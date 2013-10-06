package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.CampaignService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author: bebopjmm Date: 10/4/13 Time: 09:06
 */
@Controller
@RequestMapping("/rpgCampaigner/campaigns")
public class CampaignCommandsController {
    private static Logger LOG = LoggerFactory.getLogger(CampaignCommandsController.class);
    public static final String BASE_PATH = "/rpgCampaigner/campaigns";

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Campaign> createOrder(@RequestBody Campaign campaign, UriComponentsBuilder builder) {
        LOG.info("createOrder for " + campaign.getName() + "[" + campaign.getSlug() + "]");

        CampaignCreatedEvent campaignCreated = campaignService.createCampaign(Campaign.toCampaignDetails(campaign));
        LOG.info("campaignCreated: " + campaignCreated.getDetails().getName() + "[" + campaignCreated.getDetails().getSlug() + "]");
        Campaign newCampaign = Campaign.fromCampaignDetails(campaignCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(BASE_PATH + "/{id}").buildAndExpand(newCampaign.getSlug()).toUri());

        return new ResponseEntity<Campaign>(newCampaign, headers, HttpStatus.CREATED);
    }

}
