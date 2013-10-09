package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;

/**
 * @author John McCormick
 * Date: 10/4/13 Time: 09:07
 */
public class RestEventFixtures {

    public static CampaignCreatedEvent campaignCreated(String slug) {
        return new CampaignCreatedEvent(RestDataFixture.customCampaignDetails(slug));
    }

    public static PlayerCreatedEvent playerCreated(String username) {
        return new PlayerCreatedEvent(RestDataFixture.customPlayerDetails(username));
    }
}
