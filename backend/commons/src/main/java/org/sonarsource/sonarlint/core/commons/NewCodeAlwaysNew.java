/*
 * SonarLint Core - Commons
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
package org.sonarsource.sonarlint.core.commons;

import java.time.Instant;

public class NewCodeAlwaysNew implements NewCodeDefinition {

  NewCodeAlwaysNew() {
    // NOP
  }

  @Override
  public NewCodeMode getMode() {
    throw new UnsupportedOperationException("Mode shouldn't be called for this new code definition");
  }

  @Override
  public boolean isOnNewCode(long creationDate) {
    return true;
  }

  @Override
  public Instant getThresholdDate() {
    // instead of 0L it's set for Instant.now() in case it will be used for git blame limit (which shouldn't normally happen)
    return Instant.now();
  }

  @Override
  public boolean isSupported() {
    return true;
  }
}
