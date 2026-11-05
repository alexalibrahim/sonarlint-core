/*
 * SonarLint Core - RPC Protocol
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
package org.sonarsource.sonarlint.core.rpc.protocol.client.issue;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import org.sonarsource.sonarlint.core.rpc.protocol.backend.rules.ImpactDto;
import org.sonarsource.sonarlint.core.rpc.protocol.common.CleanCodeAttribute;
import org.sonarsource.sonarlint.core.rpc.protocol.common.Either;
import org.sonarsource.sonarlint.core.rpc.protocol.common.IssueSeverity;
import org.sonarsource.sonarlint.core.rpc.protocol.common.MQRModeDetails;
import org.sonarsource.sonarlint.core.rpc.protocol.common.RuleType;
import org.sonarsource.sonarlint.core.rpc.protocol.common.StandardModeDetails;
import org.sonarsource.sonarlint.core.rpc.protocol.common.TextRangeDto;

public class RaisedIssueDto extends RaisedFindingDto {

  private final boolean isAiCodeFixable;

  // RPC DTO: the full parameter list mirrors the protocol payload and is kept for API compatibility
  @SuppressWarnings("java:S107")
  public RaisedIssueDto(UUID id, @Nullable String serverKey, String ruleKey, String primaryMessage, Either<StandardModeDetails, MQRModeDetails> severityMode,
    Instant introductionDate, boolean isOnNewCode, boolean resolved, @Nullable TextRangeDto textRange, List<IssueFlowDto> flows, List<QuickFixDto> quickFixes,
    @Nullable String ruleDescriptionContextKey, boolean isAiCodeFixable) {
    super(id, serverKey, ruleKey, primaryMessage, severityMode, introductionDate, isOnNewCode, resolved, textRange, flows, quickFixes, ruleDescriptionContextKey);
    this.isAiCodeFixable = isAiCodeFixable;
  }

  public boolean isAiCodeFixable() {
    return isAiCodeFixable;
  }

  public Builder builder() {
    return Builder.from(this);
  }

  public static class Builder {
    private final UUID id;
    private final String serverKey;
    private final String ruleKey;
    private final String primaryMessage;
    private Either<StandardModeDetails, MQRModeDetails> severityMode;
    private final Instant introductionDate;
    private final boolean isOnNewCode;
    private boolean resolved;
    private final TextRangeDto textRange;
    private final List<IssueFlowDto> flows;
    private final List<QuickFixDto> quickFixes;
    private final String ruleDescriptionContextKey;
    private final boolean isAiCodeFixable;

    private Builder(RaisedIssueDto dto) {
      this.id = dto.getId();
      this.serverKey = dto.getServerKey();
      this.ruleKey = dto.getRuleKey();
      this.primaryMessage = dto.getPrimaryMessage();
      this.severityMode = dto.getSeverityMode();
      this.introductionDate = dto.getIntroductionDate();
      this.isOnNewCode = dto.isOnNewCode();
      this.resolved = dto.isResolved();
      this.textRange = dto.getTextRange();
      this.flows = dto.getFlows();
      this.quickFixes = dto.getQuickFixes();
      this.ruleDescriptionContextKey = dto.getRuleDescriptionContextKey();
      this.isAiCodeFixable = dto.isAiCodeFixable();
    }

    public static Builder from(RaisedIssueDto dto) {
      return new Builder(dto);
    }

    public Builder withResolution(boolean resolved) {
      this.resolved = resolved;
      return this;
    }

    public Builder withStandardModeDetails(IssueSeverity severity, RuleType type) {
      this.severityMode = Either.forLeft(new StandardModeDetails(severity, type));
      return this;
    }

    public Builder withMQRModeDetails(CleanCodeAttribute cleanCodeAttribute, List<ImpactDto> impacts) {
      this.severityMode = Either.forRight(new MQRModeDetails(cleanCodeAttribute, impacts));
      return this;
    }

    public RaisedIssueDto buildIssue() {
      return new RaisedIssueDto(id, serverKey, ruleKey, primaryMessage, severityMode, introductionDate, isOnNewCode, resolved, textRange, flows, quickFixes,
        ruleDescriptionContextKey, isAiCodeFixable);
    }
  }
}
