package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.ComplexService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.CampaignResource;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.ComplexResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ComplexController coordinates the interactions between the RESTful commands and the ComplexService.
 *
 * @author John McCormick Date: 10/15/13 Time: 10:35
 */
@Controller
@RequestMapping("/rpgCampaigner/campaigns/{campaignSlug}/locations")
public class ComplexController {
    private static Logger LOG = LoggerFactory.getLogger(ComplexController.class);
    private ComplexService complexService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ComplexResource> createComplex(@RequestBody ComplexResource complexResource,
                                                         @PathVariable String campaignSlug,
                                                         UriComponentsBuilder builder) {
        LOG.info("createComplex for " + complexResource.getName() + "[" + complexResource.getSlug()
                         + "] for campaign [" + campaignSlug + "]");
        if (complexResource == null) {
            LOG.error("complexService has NOT been injected properly into this controller!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ComplexCreatedEvent complexCreatedEvent =
                complexService.createComplex(
                        new ComplexDetails(complexResource.getName(), complexResource.getSlug()));
        LOG.info("complexCreated: " + complexCreatedEvent.getDetails().getName() + "[" +
                         complexCreatedEvent.getDetails().getSlug() + "]");
        ComplexResource newComplexResource = ComplexResource.fromComplexDetails(campaignSlug,
                                                                                complexCreatedEvent.getDetails());

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("campaignSlug", campaignSlug);
        uriVariables.put("id", newComplexResource.getSlug());
        headers.setLocation(builder.path("/rpgCampaigner/campaigns/{campaignSlug}/locations/{id}").
                buildAndExpand(uriVariables).toUri());

        return new ResponseEntity<>(newComplexResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ComplexResource> getAllComplexes(@PathVariable String campaignSlug) {
        List<ComplexResource> resources = new ArrayList<>();
        for (ComplexDetails details : complexService.getAllComplexesForCampaign(campaignSlug)) {
            resources.add(ComplexResource.fromComplexDetails(campaignSlug, details));
        }
        return resources;
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
}
