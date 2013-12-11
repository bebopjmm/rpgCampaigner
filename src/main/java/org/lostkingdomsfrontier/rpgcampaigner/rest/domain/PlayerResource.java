package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;

/**
 * The PlayerResource domain class represents an individual that participates in one or more gaming campaigns.
 *
 * @author John McCormick Date: 10/8/13 Time: 17:28
 */
public class PlayerResource {
    private String userName;

    public static PlayerDetails toPlayerDetails(PlayerResource playerResource) {
        return new PlayerDetails(playerResource.getUserName());
    }

    public static PlayerResource fromPlayerDetails(PlayerDetails details) {
        PlayerResource result = new PlayerResource();
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
