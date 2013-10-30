package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateComplexEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.ComplexService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.ComplexResource;
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

        for (ComplexDetails details : complexService.getAllComplexesForCampaign(campaignSlug)) {
            indexResource.add(
                    linkTo(ComplexController.class, campaignSlug).slash(details.getKey()).withRel(details.getName()));
        }
        return indexResource;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{complexSlug}")
    public ResponseEntity<ComplexResource> getComplex(@PathVariable String campaignSlug,
                                                      @PathVariable String complexSlug) {
        ComplexDetails details = complexService.getComplexDetails(complexSlug);
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
     * @param complexSlug
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{complexSlug}")
    public void addAreaToComplex(// AreaResource?
                                 @PathVariable String complexSlug) {

    }
}
