package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick Date: 10/4/13 Time: 10:17
 */
public class PlayerRestFixture {


    public static final String STANDARD_PLAYER_USERNAME = "bocephus";

    static List<PlayerDetails> standardPlayers;

    static {
        standardPlayers = new ArrayList<>();
        standardPlayers.add(customPlayerDetails("fred"));
        standardPlayers.add(customPlayerDetails("barney"));
        standardPlayers.add(customPlayerDetails("wilma"));
        standardPlayers.add(customPlayerDetails("betty"));
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

    public static PlayerCreatedEvent playerCreated(String username) {
        return new PlayerCreatedEvent(PlayerRestFixture.customPlayerDetails(username));
    }

    public static PlayerDetails playerNotFound() {
        return null;
    }

    public static PlayerDetails playerFound(String username) {
        return PlayerRestFixture.customPlayerDetails(username);
    }
}
