package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: bebopjmm Date: 10/8/13 Time: 17:46
 */
public class PlayerCreatedEvent {
    private final PlayerDetails details;

    public PlayerCreatedEvent(PlayerDetails details) {
        this.details = details;
    }

    public PlayerDetails getDetails() {
        return details;
    }
}
