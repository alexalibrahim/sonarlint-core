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

import org.sonarsource.sonarlint.core.local.only.LocalOnlyIssueStorageService;
import org.sonarsource.sonarlint.core.storage.StorageService;

/**
 * Groups the storage-related services needed to track findings.
 */
public class FindingsStorageServices {
  private final KnownFindingsStorageService knownFindingsStorageService;
  private final StorageService storageService;
  private final LocalOnlyIssueRepository localOnlyIssueRepository;
  private final LocalOnlyIssueStorageService localOnlyIssueStorageService;

  public FindingsStorageServices(KnownFindingsStorageService knownFindingsStorageService, StorageService storageService, LocalOnlyIssueRepository localOnlyIssueRepository,
    LocalOnlyIssueStorageService localOnlyIssueStorageService) {
    this.knownFindingsStorageService = knownFindingsStorageService;
    this.storageService = storageService;
    this.localOnlyIssueRepository = localOnlyIssueRepository;
    this.localOnlyIssueStorageService = localOnlyIssueStorageService;
  }

  public KnownFindingsStorageService getKnownFindingsStorageService() {
    return knownFindingsStorageService;
  }

  public StorageService getStorageService() {
    return storageService;
  }

  public LocalOnlyIssueRepository getLocalOnlyIssueRepository() {
    return localOnlyIssueRepository;
  }

  public LocalOnlyIssueStorageService getLocalOnlyIssueStorageService() {
    return localOnlyIssueStorageService;
  }
}
