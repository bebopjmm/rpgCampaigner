package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.ComplexRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateComplexEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick Date: 10/15/13 Time: 11:40
 */
public class ComplexEventHandler implements ComplexService {
    private static Logger LOG = LoggerFactory.getLogger(ComplexEventHandler.class);
    private final ComplexRepository complexRepository;

    public ComplexEventHandler(ComplexRepository complexRepository) {
        this.complexRepository = complexRepository;
    }

    @Override
    public ComplexCreatedEvent createComplex(CreateComplexEvent event) {
        LOG.info("createComplex");
        Complex complex = new Complex();
        complex.setName(event.getComplexName());
        complex.setCampaignSlug(event.getCampaignSlug());
        complex = complexRepository.save(complex);

        return new ComplexCreatedEvent(Complex.toComplexDetails(complex));
    }

    @Override
    public List<ComplexDetails> getAllComplexesForCampaign(String campaignSlug) {
        LOG.info("getAllComplexesForCampaign: " + campaignSlug);
        List<ComplexDetails> results = new ArrayList<>();
        for (Complex complex : complexRepository.findByCampaignSlug(campaignSlug)) {
            results.add(Complex.toComplexDetails(complex));
        }
        return results;
    }

    @Override
    public ComplexDetails getComplexDetails(String complexKey) {
        LOG.info("getComplexDetails(" + complexKey + ")");
        Complex complex = complexRepository.findOne(complexKey);
        if (complex != null) {
            return Complex.toComplexDetails(complex);
        } else {
            LOG.warn("complexKey[" + complexKey + "] NOT FOUND in repository");
            return null;
        }
    }
}
