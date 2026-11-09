/*
 * SonarLint Core - Implementation
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
package org.sonarsource.sonarlint.core.tracking;

import org.sonarsource.sonarlint.core.branch.SonarProjectBranchTrackingService;
import org.sonarsource.sonarlint.core.file.PathTranslationService;
import org.sonarsource.sonarlint.core.repository.config.ConfigurationRepository;

/**
 * Groups the services needed to resolve the binding context (binding, branch, path translation) of a configuration scope.
 */
public class BindingContextServices {
  private final ConfigurationRepository configurationRepository;
  private final SonarProjectBranchTrackingService branchTrackingService;
  private final PathTranslationService pathTranslationService;

  public BindingContextServices(ConfigurationRepository configurationRepository, SonarProjectBranchTrackingService branchTrackingService,
    PathTranslationService pathTranslationService) {
    this.configurationRepository = configurationRepository;
    this.branchTrackingService = branchTrackingService;
    this.pathTranslationService = pathTranslationService;
  }

  public ConfigurationRepository getConfigurationRepository() {
    return configurationRepository;
  }

  public SonarProjectBranchTrackingService getBranchTrackingService() {
    return branchTrackingService;
  }

  public PathTranslationService getPathTranslationService() {
    return pathTranslationService;
  }
}
