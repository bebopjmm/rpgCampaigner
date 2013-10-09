package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateCampaignEvent;

/**
 * @author John McCormick
 * Date: 10/9/13 Time: 15:42
 */
public class CampaignRestFixture {
    public static final String STANDARD_CAMPAIGN_SLUG = "rotrl";

    public static CampaignDetails customCampaignDetails(String slug) {
        return new CampaignDetails("Some Fancy CampaignResource Name", slug);
    }

    public static String newCampaignJSON() {
        return "{\"name\": \"Rise of the Runelords\", \"slug\": \"rotrl\", \"gameMasterUsername\": \"draconis\"}";
    }

    public static CampaignCreatedEvent campaignCreated(String slug) {
        return new CampaignCreatedEvent(customCampaignDetails(slug));
    }
}
