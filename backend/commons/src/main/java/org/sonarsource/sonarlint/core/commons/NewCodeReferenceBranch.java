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

public class NewCodeReferenceBranch implements NewCodeDefinition {
  private final String branchName;

  NewCodeReferenceBranch(String branchName) {
    this.branchName = branchName;
  }

  @Override
  public NewCodeMode getMode() {
    return NewCodeMode.REFERENCE_BRANCH;
  }

  @Override
  public boolean isOnNewCode(long creationDate) {
    return true;
  }

  @Override
  public boolean isSupported() {
    return false;
  }

  public String getBranchName() {
    return branchName;
  }

  @Override
  public Instant getThresholdDate() {
    // instead of Long.MAX_VALUE it's set for Instant.now() in case it will be used for git blame limit
    return Instant.now();
  }

  // Text used by IDEs in the UI. Communicate changes to IDE squad prior to changing the wording.
  @Override
  public String toString() {
    return "Current new code definition (reference branch) is not supported";
  }
}
