package org.lostkingdomsfrontier.rpgcampaigner.core.dao;

import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Simple repository interface to manage {@link Complex} instances.
 *
 * @author: John McCormick Date: 10/15/13 Time: 10:32
 */
public interface ComplexRepository extends CrudRepository<Complex, String> {

    /**
     * This method returns all Complex instances associated with the designated Campaign
     *
     * @param campaignSlug identifying slug of the Campaign to filter
     * @return list of Complex instances associated with the designated Campaign
     */
    public List<Complex> findByCampaignSlug(String campaignSlug);

}
