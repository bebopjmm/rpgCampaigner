package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.AreaRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.ComplexRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Area;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 11:40
 */
public class ComplexEventHandler implements ComplexService {
    private static Logger LOG = LoggerFactory.getLogger(ComplexEventHandler.class);
    private final ComplexRepository complexRepository;
    private final AreaRepository areaRepository;

    public ComplexEventHandler(ComplexRepository complexRepository, AreaRepository areaRepository) {
        this.complexRepository = complexRepository;
        this.areaRepository = areaRepository;
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

    @Override
    public AreaDetails addAreaToComplex(CreateAreaEvent event, String complexKey) {
        LOG.info("addAreaToComplex[" + complexKey +"]: " + event.getAreaName());
        Complex complex = complexRepository.findOne(complexKey);
        if (complex == null) {
            LOG.warn("complexKey[" + complexKey + "] NOT FOUND in repository");
            return null;
        }
        Area area = new Area();
        area.setName(event.getAreaName());
        area.setDescription(event.getAreaDescription());
        area.setDetails(event.getAreaDetails());
        area.setComplexID(complexKey);
        area = areaRepository.save(area);
        complex.getAreas().add(area);
        complex = complexRepository.save(complex);
        return Area.toAreaDetails(area);
    }

    @Override
    public List<AreaDetails> getAllAreasForComplex(String complexID) {
        LOG.info("getAllAreasForComplex: " + complexID);
        List<AreaDetails> results = new ArrayList<>();
        Complex complex = complexRepository.findOne(complexID);
        if (complex != null) {
            for (Area area : complex.getAreas()) {
                results.add(Area.toAreaDetails(area));
            }
        } else {
            LOG.warn("complexID[" + complexID + "] NOT FOUND in repository");
        }
        return results;
    }

    @Override
    public AreaDetails getAreaFromComplex(String areaID, String complexID) {
        LOG.info("getAreaFromComplex(area/complex):" + areaID + "/" + complexID);
        Area area = areaRepository.findOne(areaID);
        if (area == null) {
            LOG.warn("areaID[" + areaID + "] NOT FOUND in repository");
            return null;
        }
        if (!area.getComplexID().equals(complexID)) {
            LOG.warn("areaID[" + areaID + "] is NOT part of complex[" + complexID);
            return null;
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
