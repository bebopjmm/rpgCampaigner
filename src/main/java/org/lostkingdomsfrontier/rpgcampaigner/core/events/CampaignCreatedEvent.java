package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick Date: 10/4/13 Time: 10:12
 */
public class CampaignCreatedEvent {
    private final CampaignDetails details;

    public CampaignCreatedEvent(CampaignDetails details) {
        this.details = details;
    }

    public CampaignDetails getDetails() {
        return details;
    }
}
