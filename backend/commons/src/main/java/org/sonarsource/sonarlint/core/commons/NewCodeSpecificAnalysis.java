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

public class NewCodeSpecificAnalysis extends NewCodeDefinition.NewCodeDefinitionWithDate {
  NewCodeSpecificAnalysis(long thresholdDate) {
    super(thresholdDate);
  }

  // Text used by IDEs in the UI. Communicate changes to IDE squad prior to changing the wording.
  @Override
  public String toString() {
    return String.format("Since analysis from %s", NewCodeDefinition.formatEpochToDate(this.thresholdDate));
  }

  @Override
  public NewCodeMode getMode() {
    return NewCodeMode.SPECIFIC_ANALYSIS;
  }
}
