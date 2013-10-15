package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Simple repository interface to manage {@link Complex} instances.
 *
 * @author: John McCormick
 * Date: 10/15/13 Time: 10:32
 */
public interface ComplexRepository extends MongoRepository<Complex, String> {
}
