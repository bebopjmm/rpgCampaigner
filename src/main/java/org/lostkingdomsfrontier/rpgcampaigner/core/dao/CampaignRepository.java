package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Campaign;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple repository interface to manage {@link Campaign} instances.
 *
 * @author John McCormick Date: 10/6/13 Time: 07:54
 */
public interface CampaignRepository extends CrudRepository<Campaign, String> {

    public Campaign findBySlug(String slug);
}
