package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;

/**
 * @author: bebopjmm Date: 10/4/13 Time: 09:07
 */
public class RestEventFixtures {

    public static CampaignCreatedEvent campaignCreated(String slug) {
        return new CampaignCreatedEvent(RestDataFixture.customCampaignDetails(slug));
    }
}
