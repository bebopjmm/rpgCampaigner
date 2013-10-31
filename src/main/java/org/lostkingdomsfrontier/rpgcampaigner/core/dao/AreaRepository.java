package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Area;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Simple repository interface to manage {@link Area} instances
 *
 * @author: John McCormick
 * Date: 10/30/13 Time: 18:07
 */
public interface AreaRepository extends MongoRepository<Area, String> {
}
