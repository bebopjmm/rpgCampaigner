package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Barrier;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple repository interface to manage {@link Barrier} instances
 *
 * @author John McCormick Date: 12/14/13 Time: 14:46
 */
public interface BarrierRepository extends CrudRepository<Barrier, String> {
}
