package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateComplexEvent;

import java.util.List;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 10:34
 */
public interface ComplexService {

    public ComplexCreatedEvent createComplex(CreateComplexEvent event);

    public List<ComplexDetails> getAllComplexesForCampaign(String campaignSlug);

    public ComplexDetails getComplexDetails(String complexKey);
}
