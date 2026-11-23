/*
 * SonarLint Core - Backend CLI
 * Copyright (C) 2016-2025 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.sonarlint.core.backend.cli;

import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.sonarlint.core.rpc.impl.BackendJsonRpcLauncher;
import picocli.CommandLine;

@CommandLine.Command(name = "slcore", mixinStandardHelpOptions = true, description = "The SonarLint Core backend")
public class SonarLintServerCli implements Callable<Integer> {

  private static final Logger LOG = LoggerFactory.getLogger(SonarLintServerCli.class);

  @Override
  public Integer call() {
    return run(System.in, System.out);
  }

  int run(InputStream originalStdIn, PrintStream originalStdOut) {
    var inputStream = new EndOfStreamAwareInputStream(originalStdIn);
    System.setIn(new ByteArrayInputStream(new byte[0]));
    // Redirect all logs to stderr for now, would be better to go to a file later
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.err), true));

    try {
      var rpcLauncher = new BackendJsonRpcLauncher(inputStream, originalStdOut);
      var rpcServer = rpcLauncher.getServer();
      inputStream.onExit().thenRun(() -> {
        if (!rpcServer.isReaderShutdown()) {
          LOG.info("Input stream has closed, exiting...");
          rpcServer.shutdown();
        }
      });
      rpcServer.getClientListener().get();
    } catch (CancellationException shutdown) {
      LOG.info("Server is shutting down...");
    } catch (InterruptedException e) {
      LOG.error("Interrupted while waiting for client listener", e);
      Thread.currentThread().interrupt();
      return -1;
    } catch (Exception e) {
      LOG.error("Error while running the server", e);
      return -1;
    }

    return 0;
  }

  public static void main(String... args) {
    var exitCode = new CommandLine(new SonarLintServerCli()).execute(args);
    System.exit(exitCode);
  }
}
