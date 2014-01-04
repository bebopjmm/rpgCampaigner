package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.*;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.ComplexService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.AreaResource;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.ComplexResource;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.TransitionResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The ComplexController coordinates the interactions between the RESTful commands and the ComplexService.
 *
 * @author John McCormick Date: 10/15/13 Time: 10:35
 */
@Controller
@RequestMapping("/rpgCampaigner/campaigns/{campaignSlug}/locations")
public class ComplexController {
    private static Logger LOG = LoggerFactory.getLogger(ComplexController.class);
    @Autowired
    private ComplexService complexService;

    /**
     * Adds the provided Complex to the designated Campaign. Uses POST because the semantics are adding something (the
     * Complex) to a container (the Campaign).
     * <p/>
     * TODO Handle situation where complex.key is already being used.
     *
     * @param complexResource
     * @param campaignSlug
     * @param builder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ComplexResource> createComplex(@RequestBody ComplexResource complexResource,
                                                         @PathVariable String campaignSlug,
                                                         UriComponentsBuilder builder) {
        LOG.info("createComplex for " + complexResource.getName() + " for campaign [" + campaignSlug + "]");
        if (complexResource == null) {
            LOG.error("complexService has NOT been injected properly into this controller!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ComplexCreatedEvent complexCreatedEvent =
                complexService.createComplex(new CreateComplexEvent(complexResource.getName(), campaignSlug));
        LOG.info("complexCreated: " + complexCreatedEvent.getDetails().getName() + "[" +
                         complexCreatedEvent.getDetails().getKey() + "]");
        ComplexResource newComplexResource = ComplexResource.fromComplexDetails(campaignSlug,
                                                                                complexCreatedEvent.getDetails());

        HttpHeaders headers = new HttpHeaders();
        LOG.info("Header Location = " + newComplexResource.getId().toString());
        // TODO headers.setLocation
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("campaignSlug", campaignSlug);
//        uriVariables.put("id", newComplexResource.g);
//        headers.setLocation(builder.path("/rpgCampaigner/campaigns/{campaignSlug}/locations/{id}").
//                buildAndExpand(uriVariables).toUri());

        return new ResponseEntity<>(newComplexResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResourceSupport getComplexesIndexForCampaign(@PathVariable String campaignSlug) {
        ResourceSupport indexResource = new ResourceSupport();
        List<ComplexDetails> complexList = complexService.getAllComplexesForCampaign(campaignSlug);
        if (complexList == null) return indexResource;

        for (ComplexDetails details : complexList) {
            indexResource.add(
                    linkTo(ComplexController.class, campaignSlug).slash(details.getKey()).withRel(details.getName()));
        }
        return indexResource;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{complexID}")
    public ResponseEntity<ComplexResource> getComplex(@PathVariable String campaignSlug,
                                                      @PathVariable String complexID) {
        ComplexDetails details = complexService.getComplexDetails(complexID);
        if (details != null) {
            ComplexResource resource = ComplexResource.fromComplexDetails(campaignSlug, details);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new Area to the designated Complex. Uses POST because the semantics are adding something (the Area) to a
     * container (the Complex).
     *
     * @param areaResource
     * @param complexID
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{complexID}/areas")
    public ResponseEntity<AreaResource> addAreaToComplex(@RequestBody AreaResource areaResource,
                                                         @PathVariable String campaignSlug,
                                                         @PathVariable String complexID) {

        LOG.info("addAreaToComplex for " + areaResource.getName() + " for complex [" + complexID + "]");
        if (areaResource == null) {
            LOG.error("areaResource has NOT been injected properly into this controller!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        AreaDetails createdArea = complexService.addAreaToComplex(
                new CreateAreaEvent(areaResource.getName(), areaResource.getDescription(), areaResource.getDetails()),
                complexID);
        LOG.info("areaCreated: " + createdArea.getName() + "[" + createdArea.getKey() + "]");
        AreaResource newAreaResource = AreaResource.fromAreaDetails(createdArea, campaignSlug);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(ComplexController.class, campaignSlug).slash(complexID).slash("areas")
                                    .slash(createdArea.getKey()).toUri());
        LOG.info("Header Location = " + headers.getLocation().toString());

        return new ResponseEntity<>(newAreaResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{complexID}/areas")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResourceSupport getAreasIndexForComplex(@PathVariable String campaignSlug, @PathVariable String complexID) {
        ResourceSupport indexResource = new ResourceSupport();
        List<AreaDetails> areaList = complexService.getAllAreasForComplex(complexID);
        if (areaList == null) return indexResource;

        for (AreaDetails details : areaList) {
            indexResource.add(
                    linkTo(ComplexController.class, campaignSlug).slash(details.getComplexID()).slash("areas")
                            .slash(details.getKey()).withRel(details.getName()));
        }
        return indexResource;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{complexID}/areas/{areaID}")
    public ResponseEntity<AreaResource> getArea(@PathVariable String campaignSlug,
                                                @PathVariable String complexID,
                                                @PathVariable String areaID) {
        AreaDetails details = complexService.getAreaFromComplex(areaID, complexID);
        if (details != null) {
            AreaResource resource = AreaResource.fromAreaDetails(details, campaignSlug);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new Transition to the designated Complex. Uses POST because the semantics are adding something (the
     * Transition) to a container (the Complex).
     *
     * @param transitionResource
     * @param complexID
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{complexID}/transitions")
    public ResponseEntity<TransitionResource> addTransitionToComplex(@RequestBody TransitionResource transitionResource,
                                                                     @PathVariable String campaignSlug,
                                                                     @PathVariable String complexID) {

        LOG.info("addTransitionToComplex for " + transitionResource.getName() + " for complex [" + complexID + "]");
        if (transitionResource == null) {
            LOG.error("transitionResource has NOT been injected properly into this controller!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TransitionDetails createdTransition = complexService.addTransitionToComplex(
                new CreateBarrierEvent(transitionResource.getName(), transitionResource.getDescription()),
                complexID);
        LOG.info("transitionCreated: " + createdTransition.getName() + "[" + createdTransition.getKey() + "]");
        TransitionResource newResource = TransitionResource.fromTransitionDetails(createdTransition, campaignSlug);

        HttpHeaders headers = new HttpHeaders();
        LOG.info("Header Location = " + newResource.getId());

        return new ResponseEntity<>(newResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{complexID}/transitions")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResourceSupport getTransitionIndexForComplex(@PathVariable String campaignSlug,
                                                        @PathVariable String complexID) {
        ResourceSupport indexResource = new ResourceSupport();
        List<TransitionDetails> transitionList = complexService.getAllTransitionsForComplex(complexID);
        if (transitionList == null) return indexResource;

        for (TransitionDetails details : transitionList) {
            indexResource.add(
                    linkTo(ComplexController.class, campaignSlug).slash(details.getComplexID()).slash("transitions")
                            .slash(details.getKey()).withRel(details.getName()));
        }
        return indexResource;
    }
}
