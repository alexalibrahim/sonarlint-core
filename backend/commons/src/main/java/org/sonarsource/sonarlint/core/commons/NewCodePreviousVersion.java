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

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

public class NewCodePreviousVersion extends NewCodeDefinition.NewCodeDefinitionWithDate {
  private final String version;

  NewCodePreviousVersion(long thresholdDate, @Nullable String version) {
    super(thresholdDate);
    this.version = version;
  }

  // Text used by IDEs in the UI. Communicate changes to IDE squad prior to changing the wording.
  @Override
  public String toString() {
    var versionQualifier = (version == null) ? NewCodeDefinition.formatEpochToDate(this.thresholdDate) : ("version " + version);
    return String.format("Since %s", versionQualifier);
  }

  @Override
  public NewCodeMode getMode() {
    return NewCodeMode.PREVIOUS_VERSION;
  }

  @CheckForNull
  public String getVersion() {
    return version;
  }
}
