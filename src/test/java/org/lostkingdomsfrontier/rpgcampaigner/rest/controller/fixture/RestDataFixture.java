package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;

/**
 * @author: bebopjmm Date: 10/4/13 Time: 10:17
 */
public class RestDataFixture {

    public static CampaignDetails customCampaignDetails(String slug) {
        return new CampaignDetails("My Test Campaign", slug);
    }

    public static String standardCampaignJSON() {
        return "{\"name\": \"Rise of the Runelords\", \"slug\": \"rotrl\"}";
    }
}
