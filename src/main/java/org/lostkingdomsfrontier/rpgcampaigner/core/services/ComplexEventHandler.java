package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.ComplexRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 11:40
 */
public class ComplexEventHandler implements ComplexService {
    private static Logger LOG = LoggerFactory.getLogger(ComplexEventHandler.class);

    private final ComplexRepository complexRepository;

    public ComplexEventHandler(ComplexRepository complexRepository) {
        this.complexRepository = complexRepository;
    }

    @Override
    public ComplexCreatedEvent createComplex(ComplexDetails complexDetails) {
        LOG.info("createComplex");
        Complex complex = new Complex();
        complex.setName(complexDetails.getName());
        complex.setSlug(complexDetails.getSlug());
        complex = complexRepository.save(complex);

        return new ComplexCreatedEvent(Complex.toComplexDetails(complex));
    }
}
