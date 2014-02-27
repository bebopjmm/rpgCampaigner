package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple repository interface to manage {@link Player} instances.
 *
 * @author John McCormick Date: 10/8/13 Time: 17:31
 */
public interface PlayerRepository extends CrudRepository<Player, String> {

    public Player findByUsername(String username);
}
