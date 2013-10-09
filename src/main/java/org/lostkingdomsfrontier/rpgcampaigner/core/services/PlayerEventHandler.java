package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.PlayerRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Player;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author John McCormick
 * Date: 10/8/13 Time: 18:09
 */
public class PlayerEventHandler implements PlayerService {
    private static Logger LOG = LoggerFactory.getLogger(PlayerEventHandler.class);
    private final PlayerRepository repository;

    public PlayerEventHandler(final PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlayerCreatedEvent createPlayer(PlayerDetails details) {
        LOG.info("createPlayer");
        Player player = Player.fromPlayerDetails(details);
        player = repository.save(player);
        return new PlayerCreatedEvent(Player.toPlayerDetails(player));
    }
}
