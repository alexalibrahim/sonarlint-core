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
package org.sonarsource.sonarlint.core.rpc.protocol.client.hotspot;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import org.sonarsource.sonarlint.core.rpc.protocol.backend.hotspot.HotspotStatus;
import org.sonarsource.sonarlint.core.rpc.protocol.backend.rules.VulnerabilityProbability;
import org.sonarsource.sonarlint.core.rpc.protocol.client.issue.IssueFlowDto;
import org.sonarsource.sonarlint.core.rpc.protocol.client.issue.QuickFixDto;
import org.sonarsource.sonarlint.core.rpc.protocol.client.issue.RaisedFindingDto;
import org.sonarsource.sonarlint.core.rpc.protocol.common.Either;
import org.sonarsource.sonarlint.core.rpc.protocol.common.MQRModeDetails;
import org.sonarsource.sonarlint.core.rpc.protocol.common.StandardModeDetails;
import org.sonarsource.sonarlint.core.rpc.protocol.common.TextRangeDto;

public class RaisedHotspotDto extends RaisedFindingDto {

  private final HotspotStatus status;
  private final VulnerabilityProbability vulnerabilityProbability;

  private RaisedHotspotDto(Builder builder) {
    super(builder.id, builder.serverKey, builder.ruleKey, builder.primaryMessage, builder.severityMode, builder.introductionDate, builder.isOnNewCode, builder.resolved,
      builder.textRange, builder.flows, builder.quickFixes, builder.ruleDescriptionContextKey);
    this.vulnerabilityProbability = builder.vulnerabilityProbability;
    this.status = builder.status;
  }

  public static Builder builder() {
    return new Builder();
  }

  @CheckForNull
  public VulnerabilityProbability getVulnerabilityProbability() {
    return vulnerabilityProbability;
  }

  public HotspotStatus getStatus() {
    return status;
  }

  public RaisedHotspotDto withHotspotStatusAndResolution(HotspotStatus hotspotStatus, boolean resolved) {
    return builder()
      .withId(getId())
      .withServerKey(getServerKey())
      .withRuleKey(getRuleKey())
      .withPrimaryMessage(getPrimaryMessage())
      .withSeverityMode(getSeverityMode())
      .withIntroductionDate(getIntroductionDate())
      .withOnNewCode(isOnNewCode())
      .withResolved(resolved)
      .withTextRange(getTextRange())
      .withFlows(getFlows())
      .withQuickFixes(getQuickFixes())
      .withRuleDescriptionContextKey(getRuleDescriptionContextKey())
      .withVulnerabilityProbability(getVulnerabilityProbability())
      .withStatus(hotspotStatus)
      .build();
  }

  public static class Builder {
    private UUID id;
    private String serverKey;
    private String ruleKey;
    private String primaryMessage;
    private Either<StandardModeDetails, MQRModeDetails> severityMode;
    private Instant introductionDate;
    private boolean isOnNewCode;
    private boolean resolved;
    private TextRangeDto textRange;
    private List<IssueFlowDto> flows;
    private List<QuickFixDto> quickFixes;
    private String ruleDescriptionContextKey;
    private VulnerabilityProbability vulnerabilityProbability;
    private HotspotStatus status;

    private Builder() {
    }

    public Builder withId(UUID id) {
      this.id = id;
      return this;
    }

    public Builder withServerKey(@Nullable String serverKey) {
      this.serverKey = serverKey;
      return this;
    }

    public Builder withRuleKey(String ruleKey) {
      this.ruleKey = ruleKey;
      return this;
    }

    public Builder withPrimaryMessage(String primaryMessage) {
      this.primaryMessage = primaryMessage;
      return this;
    }

    public Builder withSeverityMode(Either<StandardModeDetails, MQRModeDetails> severityMode) {
      this.severityMode = severityMode;
      return this;
    }

    public Builder withIntroductionDate(Instant introductionDate) {
      this.introductionDate = introductionDate;
      return this;
    }

    public Builder withOnNewCode(boolean isOnNewCode) {
      this.isOnNewCode = isOnNewCode;
      return this;
    }

    public Builder withResolved(boolean resolved) {
      this.resolved = resolved;
      return this;
    }

    public Builder withTextRange(@Nullable TextRangeDto textRange) {
      this.textRange = textRange;
      return this;
    }

    public Builder withFlows(List<IssueFlowDto> flows) {
      this.flows = flows;
      return this;
    }

    public Builder withQuickFixes(List<QuickFixDto> quickFixes) {
      this.quickFixes = quickFixes;
      return this;
    }

    public Builder withRuleDescriptionContextKey(@Nullable String ruleDescriptionContextKey) {
      this.ruleDescriptionContextKey = ruleDescriptionContextKey;
      return this;
    }

    public Builder withVulnerabilityProbability(@Nullable VulnerabilityProbability vulnerabilityProbability) {
      this.vulnerabilityProbability = vulnerabilityProbability;
      return this;
    }

    public Builder withStatus(HotspotStatus status) {
      this.status = status;
      return this;
    }

    public RaisedHotspotDto build() {
      return new RaisedHotspotDto(this);
    }
  }
}
