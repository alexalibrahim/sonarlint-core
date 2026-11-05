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

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import org.sonarsource.sonarlint.core.analysis.api.Flow;
import org.sonarsource.sonarlint.core.analysis.api.QuickFix;
import org.sonarsource.sonarlint.core.commons.CleanCodeAttribute;
import org.sonarsource.sonarlint.core.commons.ImpactSeverity;
import org.sonarsource.sonarlint.core.commons.IssueSeverity;
import org.sonarsource.sonarlint.core.commons.LineWithHash;
import org.sonarsource.sonarlint.core.commons.RuleType;
import org.sonarsource.sonarlint.core.commons.SoftwareQuality;
import org.sonarsource.sonarlint.core.commons.VulnerabilityProbability;
import org.sonarsource.sonarlint.core.commons.api.TextRangeWithHash;
import org.sonarsource.sonarlint.core.rpc.protocol.backend.hotspot.HotspotStatus;

public class TrackedIssue {
  private final UUID id;
  private final String message;
  private final String ruleKey;
  private final TextRangeWithHash textRangeWithHash;
  private final LineWithHash lineWithHash;
  private final String serverKey;
  private final Instant introductionDate;
  private final boolean resolved;
  private final IssueSeverity severity;
  private final RuleType type;
  private final Map<SoftwareQuality, ImpactSeverity> impacts;
  private final List<Flow> flows;
  private final List<QuickFix> quickFixes;
  private final VulnerabilityProbability vulnerabilityProbability;
  private final HotspotStatus hotspotStatus;
  private final String ruleDescriptionContextKey;
  private final CleanCodeAttribute cleanCodeAttribute;
  private final URI fileUri;

  private TrackedIssue(Builder builder) {
    this.id = builder.id;
    this.message = builder.message;
    this.ruleKey = builder.ruleKey;
    this.textRangeWithHash = builder.textRangeWithHash;
    this.lineWithHash = builder.lineWithHash;
    this.serverKey = builder.serverKey;
    this.introductionDate = builder.introductionDate;
    this.resolved = builder.resolved;
    this.severity = builder.severity;
    this.type = builder.type;
    this.impacts = builder.impacts;
    this.flows = builder.flows;
    this.quickFixes = builder.quickFixes;
    this.vulnerabilityProbability = builder.vulnerabilityProbability;
    this.hotspotStatus = builder.hotspotStatus;
    this.ruleDescriptionContextKey = builder.ruleDescriptionContextKey;
    this.cleanCodeAttribute = builder.cleanCodeAttribute;
    this.fileUri = builder.fileUri;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(TrackedIssue trackedIssue) {
    return new Builder()
      .id(trackedIssue.id)
      .message(trackedIssue.message)
      .introductionDate(trackedIssue.introductionDate)
      .resolved(trackedIssue.resolved)
      .severity(trackedIssue.severity)
      .type(trackedIssue.type)
      .ruleKey(trackedIssue.ruleKey)
      .textRangeWithHash(trackedIssue.textRangeWithHash)
      .lineWithHash(trackedIssue.lineWithHash)
      .serverKey(trackedIssue.serverKey)
      .impacts(trackedIssue.impacts)
      .flows(trackedIssue.flows)
      .quickFixes(trackedIssue.quickFixes)
      .vulnerabilityProbability(trackedIssue.vulnerabilityProbability)
      .hotspotStatus(trackedIssue.hotspotStatus)
      .ruleDescriptionContextKey(trackedIssue.ruleDescriptionContextKey)
      .cleanCodeAttribute(trackedIssue.cleanCodeAttribute)
      .fileUri(trackedIssue.fileUri);
  }

  public static class Builder {
    private UUID id;
    private String message;
    private String ruleKey;
    private TextRangeWithHash textRangeWithHash;
    private LineWithHash lineWithHash;
    private String serverKey;
    private Instant introductionDate;
    private boolean resolved;
    private IssueSeverity severity;
    private RuleType type;
    private Map<SoftwareQuality, ImpactSeverity> impacts;
    private List<Flow> flows;
    private List<QuickFix> quickFixes;
    private VulnerabilityProbability vulnerabilityProbability;
    private HotspotStatus hotspotStatus;
    private String ruleDescriptionContextKey;
    private CleanCodeAttribute cleanCodeAttribute;
    private URI fileUri;

    private Builder() {
    }

    public Builder id(UUID id) {
      this.id = id;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder introductionDate(@Nullable Instant introductionDate) {
      this.introductionDate = introductionDate;
      return this;
    }

    public Builder resolved(boolean resolved) {
      this.resolved = resolved;
      return this;
    }

    public Builder severity(IssueSeverity severity) {
      this.severity = severity;
      return this;
    }

    public Builder type(RuleType type) {
      this.type = type;
      return this;
    }

    public Builder ruleKey(String ruleKey) {
      this.ruleKey = ruleKey;
      return this;
    }

    public Builder textRangeWithHash(@Nullable TextRangeWithHash textRangeWithHash) {
      this.textRangeWithHash = textRangeWithHash;
      return this;
    }

    public Builder lineWithHash(@Nullable LineWithHash lineWithHash) {
      this.lineWithHash = lineWithHash;
      return this;
    }

    public Builder serverKey(@Nullable String serverKey) {
      this.serverKey = serverKey;
      return this;
    }

    public Builder impacts(Map<SoftwareQuality, ImpactSeverity> impacts) {
      this.impacts = impacts;
      return this;
    }

    public Builder flows(List<Flow> flows) {
      this.flows = flows;
      return this;
    }

    public Builder quickFixes(List<QuickFix> quickFixes) {
      this.quickFixes = quickFixes;
      return this;
    }

    public Builder vulnerabilityProbability(@Nullable VulnerabilityProbability vulnerabilityProbability) {
      this.vulnerabilityProbability = vulnerabilityProbability;
      return this;
    }

    public Builder hotspotStatus(@Nullable HotspotStatus hotspotStatus) {
      this.hotspotStatus = hotspotStatus;
      return this;
    }

    public Builder ruleDescriptionContextKey(@Nullable String ruleDescriptionContextKey) {
      this.ruleDescriptionContextKey = ruleDescriptionContextKey;
      return this;
    }

    public Builder cleanCodeAttribute(CleanCodeAttribute cleanCodeAttribute) {
      this.cleanCodeAttribute = cleanCodeAttribute;
      return this;
    }

    public Builder fileUri(@Nullable URI fileUri) {
      this.fileUri = fileUri;
      return this;
    }

    public TrackedIssue build() {
      return new TrackedIssue(this);
    }
  }

  public UUID getId() {
    return id;
  }

  @CheckForNull
  public String getServerKey() {
    return serverKey;
  }

  public Instant getIntroductionDate() {
    return introductionDate;
  }

  public boolean isResolved() {
    return resolved;
  }

  public IssueSeverity getSeverity() {
    return severity;
  }

  public RuleType getType() {
    return type;
  }

  public boolean isSecurityHotspot() {
    return getType() == RuleType.SECURITY_HOTSPOT;
  }

  public String getRuleKey() {
    return ruleKey;
  }

  @CheckForNull
  public TextRangeWithHash getTextRangeWithHash() {
    return textRangeWithHash;
  }

  public String getMessage() {
    return message;
  }

  public Map<SoftwareQuality, ImpactSeverity> getImpacts() {
    return impacts;
  }

  public List<Flow> getFlows() {
    return flows;
  }

  public List<QuickFix> getQuickFixes() {
    return quickFixes;
  }

  public VulnerabilityProbability getVulnerabilityProbability() {
    return vulnerabilityProbability;
  }

  @CheckForNull
  public String getRuleDescriptionContextKey() {
    return ruleDescriptionContextKey;
  }

  public CleanCodeAttribute getCleanCodeAttribute() {
    return cleanCodeAttribute;
  }

  @CheckForNull
  public LineWithHash getLineWithHash() {
    return lineWithHash;
  }

  @CheckForNull
  public URI getFileUri() {
    return fileUri;
  }

  @CheckForNull
  public HotspotStatus getHotspotStatus() {
    return hotspotStatus;
  }
}
