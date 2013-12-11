package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick Date: 10/9/13 Time: 15:42
 */
public class CampaignRestFixture {
    public static final String STANDARD_CAMPAIGN_SLUG = "rotrl";

    static List<CampaignDetails> standardCampaigns;

    static {
        standardCampaigns = new ArrayList<>();
        standardCampaigns.add(CampaignRestFixture.customCampaignDetails("rotrl"));
        standardCampaigns.add(CampaignRestFixture.customCampaignDetails("cot"));
        standardCampaigns.add(CampaignRestFixture.customCampaignDetails("kingmaker"));
        standardCampaigns.add(CampaignRestFixture.customCampaignDetails("serpent-isle"));
    }

    public static CampaignDetails customCampaignDetails(String slug) {
        return new CampaignDetails("Some Fancy CampaignResource Name", slug);
    }

    public static String newCampaignJSON() {
        return "{\"name\": \"Rise of the Runelords\", \"slug\": \"rotrl\", \"gameMasterUsername\": \"draconis\"}";
    }

    public static CampaignCreatedEvent campaignCreated(String slug) {
        return new CampaignCreatedEvent(customCampaignDetails(slug));
    }

    public static List<CampaignDetails> allCampaigns() {
        return standardCampaigns;
    }

    public static CampaignDetails campaignFound(String slug) {
        return CampaignRestFixture.customCampaignDetails(slug);
    }
}
