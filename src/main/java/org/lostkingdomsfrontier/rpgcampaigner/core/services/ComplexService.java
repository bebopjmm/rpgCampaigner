package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 10:34
 */
public interface ComplexService {

    public ComplexCreatedEvent createComplex(ComplexDetails complexDetails);
}
