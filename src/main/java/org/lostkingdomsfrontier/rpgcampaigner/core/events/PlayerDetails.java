package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick
 * Date: 10/8/13 Time: 17:35
 */
public class PlayerDetails {
    private final String username;

    public PlayerDetails(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
