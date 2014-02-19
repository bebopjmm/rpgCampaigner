package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.AreaRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.BarrierRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.ComplexRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Area;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Barrier;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.*;
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
    private final AreaRepository areaRepository;
    private final BarrierRepository barrierRepository;

    public ComplexEventHandler(ComplexRepository complexRepository, 
    							AreaRepository areaRepository,
    							BarrierRepository barrierRepository) {
        this.complexRepository = complexRepository;
        this.areaRepository = areaRepository;
        this.barrierRepository = barrierRepository;
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
    public ComplexDetails getComplexDetails(String complexID) {
        LOG.info("getComplexDetails(" + complexID + ")");
        Complex complex = complexRepository.findOne(complexID);
        if (complex != null) {
            return Complex.toComplexDetails(complex);
        } else {
            LOG.warn("complexID[" + complexID + "] NOT FOUND in repository");
            return null;
        }
    }

    @Override
    public AreaDetails addAreaToComplex(CreateAreaEvent event, String complexID) {
        LOG.info("addAreaToComplex[" + complexID + "]: " + event.getAreaName());
        Complex complex = complexRepository.findOne(complexID);
        if (complex == null) {
            LOG.warn("complexID[" + complexID + "] NOT FOUND in repository");
            return null;
        }
        Area area = new Area();
        area.setName(event.getAreaName());
        area.setDescription(event.getAreaDescription());
        area.setDetails(event.getAreaDetails());
        area.setComplexID(complexID);
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
        return Area.toAreaDetails(area);
    }

    @Override
    public List<AreaDetails> linkAreasWithExit(LinkAreasEvent event, String complexID) {
        LOG.info("Linking Areas " + event.getAreaID1() + " and " + event.getAreaID2());
        List<AreaDetails> results = new ArrayList<>(2);
        Area area1 = areaRepository.findOne(event.getAreaID1());
        Area area2 = areaRepository.findOne(event.getAreaID2());

        // Check that our retrieves are sane.
        if (area1 == null) {
            LOG.warn("areaID[" + event.getAreaID1() + "] NOT FOUND in repository");
            return results;
        }
        if (!area1.getComplexID().equals(complexID)) {
            LOG.warn("areaID[" + event.getAreaID1() + "] is NOT part of complex[" + complexID);
            return results;
        }
        if (area2 == null) {
            LOG.warn("areaID[" + event.getAreaID2() + "] NOT FOUND in repository");
            return results;
        }
        if (!area2.getComplexID().equals(complexID)) {
            LOG.warn("areaID[" + event.getAreaID2() + "] is NOT part of complex[" + complexID);
            return results;
        }

        // Create the shared Barrier for the Exit elements
        Barrier barrier = new Barrier();
        barrier.setDescription(event.getCreateBarrierEvent().getDescription());
        barrier.setOpen(event.getCreateBarrierEvent().isOpen());
        barrier = this.barrierRepository.save(barrier);
        
        // Create the Exit elements for the two areas, linking them together
        Area.Exit exit = new Area.Exit();
        exit.setNextArea(area2);
        exit.setBarrier(barrier);
        area1.getExits().add(exit);
        area1 = areaRepository.save(area1);

        exit = new Area.Exit();
        exit.setNextArea(area1);
        if (barrier == null) {
            LOG.warn("NULL barrier being assigned to area2!");
        }
        exit.setBarrier(barrier);
        area2.getExits().add(exit);
        area2 = areaRepository.save(area2);

        // Build and return our results
        results.add(Area.toAreaDetails(area1));
        results.add(Area.toAreaDetails(area2));
        return results;
    }

//    @Override
//    public TransitionDetails addBarrierToComplex(CreateBarrierEvent event, String complexID) {
//        LOG.info("addTransitionToComplex[" + complexID + "]: " + event.getName());
//        Complex complex = complexRepository.findOne(complexID);
//        if (complex == null) {
//            LOG.warn("complexID[" + complexID + "] NOT FOUND in repository");
//            return null;
//        }
//        Barrier barrier = new Barrier();
//        barrier.setComplexID(complexID);
//        barrier.setOpen(true);
//        barrier = barrierRepository.save(barrier);
//        complex.getBarriers().add(barrier);
//        complex = complexRepository.save(complex);
//        return Transition.toTransitionDetails(transition);
//    }

//    @Override
//    public List<TransitionDetails> getAllBarriersForComplex(String complexID) {
//        LOG.info("getAllTransitionsForComplex: " + complexID);
//        List<TransitionDetails> results = new ArrayList<>();
//        Complex complex = complexRepository.findOne(complexID);
//        if (complex != null) {
//            for (Barrier barrier : complex.getBarriers()) {
//                results.add(Transition.toTransitionDetails(transition));
//            }
//        } else {
//            LOG.warn("complexID[" + complexID + "] NOT FOUND in repository");
//        }
//        return results;
//    }
}
