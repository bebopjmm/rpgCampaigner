package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;

import java.util.List;

/**
 * @author John McCormick Date: 10/8/13 Time: 17:30
 */
public interface PlayerService {

    public PlayerCreatedEvent createPlayer(PlayerDetails details);

    public PlayerDetails getPlayerDetails(String username);

    public List<PlayerDetails> getAllPlayerDetails();
}
