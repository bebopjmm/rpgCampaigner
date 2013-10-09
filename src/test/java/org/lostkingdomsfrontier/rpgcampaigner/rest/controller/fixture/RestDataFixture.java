package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick
 * Date: 10/4/13 Time: 10:17
 */
public class RestDataFixture {

    public static final String STANDARD_CAMPAIGN_SLUG = "rotrl";
    public static final String STANDARD_PLAYER_USERNAME = "bocephus";

    static List<PlayerDetails> standardPlayers;

    static {
        standardPlayers = new ArrayList<>();
        standardPlayers.add(customPlayerDetails("fred"));
        standardPlayers.add(customPlayerDetails("barney"));
        standardPlayers.add(customPlayerDetails("wilma"));
        standardPlayers.add(customPlayerDetails("betty"));
    }

    public static CampaignDetails customCampaignDetails(String slug) {
        return new CampaignDetails("Some Fancy Campaign Name", slug);
    }

    public static String standardCampaignJSON() {
        return "{\"name\": \"Rise of the Runelords\", \"slug\": \"rotrl\"}";
    }

    public static PlayerDetails customPlayerDetails(String username) {
        return new PlayerDetails(username);
    }

    public static String standardPlayerJSON() {
        return "{\"userName\": \"bocephus\"}";
    }

    public static List<PlayerDetails> allPlayers() {
        return standardPlayers;
    }
}
