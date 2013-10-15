package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.ComplexService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.ComplexResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
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
            return new ResponseEntity<ComplexResource>(HttpStatus.INTERNAL_SERVER_ERROR);
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

        return new ResponseEntity<ComplexResource>(newComplexResource, headers, HttpStatus.CREATED);
    }
}
