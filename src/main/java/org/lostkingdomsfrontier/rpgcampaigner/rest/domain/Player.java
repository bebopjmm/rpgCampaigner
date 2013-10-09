package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;

/**
 * The Player domain class represents an individual that participates in one or more gaming campaigns.
 *
 * @author John McCormick
 * Date: 10/8/13 Time: 17:28
 */
public class Player {
    private String userName;

    public static PlayerDetails toPlayerDetails(Player player) {
        return new PlayerDetails(player.getUserName());
    }

    public static Player fromPlayerDetails(PlayerDetails details) {
        Player result = new Player();
        result.setUserName(details.getUsername());
        return result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
