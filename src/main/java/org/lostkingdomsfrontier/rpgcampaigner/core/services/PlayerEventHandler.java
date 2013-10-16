package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.PlayerRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Player;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PlayerDetails getPlayerDetails(String username) {
        LOG.info("getPlayerDetails(" + username + ")");
        Player player = repository.findByUsername(username);
        if (player != null) {
            return Player.toPlayerDetails(player);
        } else {
            LOG.warn("username[" + username + "] NOT FOUND in repository");
            return null;
        }
    }

    @Override
    public List<PlayerDetails> getAllPlayerDetails() {
        LOG.info("getAllPlayerDetails");
        List<PlayerDetails> results = new ArrayList<>();
        for (Player player : repository.findAll()) {
            results.add(Player.toPlayerDetails(player));
        }
        return results;
    }
}