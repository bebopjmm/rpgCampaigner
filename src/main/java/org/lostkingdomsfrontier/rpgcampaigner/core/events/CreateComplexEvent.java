package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: John McCormick Date: 10/30/13 Time: 13:14
 */
public class CreateComplexEvent {
    private final String complexName;

    private final String campaignSlug;

    public CreateComplexEvent(String complexName, String campaignSlug) {
        this.complexName = complexName;
        this.campaignSlug = campaignSlug;
    }

    public String getComplexName() {
        return complexName;
    }

    public String getCampaignSlug() {
        return campaignSlug;
    }
}
