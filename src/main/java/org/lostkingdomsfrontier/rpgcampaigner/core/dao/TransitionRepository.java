package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Transition;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Simple repository interface to manage {@link Transition} instances
 *
 * @author: John McCormick Date: 10/31/13 Time: 14:35
 */
public interface TransitionRepository extends MongoRepository<Transition, String> {
}
