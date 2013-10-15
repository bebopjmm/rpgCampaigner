package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateCampaignEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.CampaignService;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.CampaignResource;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.NewCampaignResource;
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
 * The CampaignController coordinates the interactions between the RESTful commands and the CampaignService.
 *
 * @author John McCormick Date: 10/4/13 Time: 09:06
 */
@Controller
@RequestMapping(CampaignController.BASE_PATH)
public class CampaignController {
    public static final String BASE_PATH = "/rpgCampaigner/campaigns";
    private static Logger LOG = LoggerFactory.getLogger(CampaignController.class);
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CampaignResource> createCampaign(@RequestBody NewCampaignResource campaignResource,
                                                           UriComponentsBuilder builder) {
        LOG.info("createCampaign for " + campaignResource.getName() + "[" + campaignResource.getSlug()
                         + "] for gameMaster [" + campaignResource.getGameMasterUsername() + "]");
        if (playerService == null) {
            LOG.error("playerService has NOT been injected properly into this controller!");
            return new ResponseEntity<CampaignResource>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PlayerDetails gameMaster = playerService.getPlayerDetails(campaignResource.getGameMasterUsername());
        if (gameMaster == null) {
            return new ResponseEntity<CampaignResource>(HttpStatus.FAILED_DEPENDENCY);
        }

        CampaignCreatedEvent campaignCreated = campaignService.createCampaign(
                new CreateCampaignEvent(campaignResource.getName(), campaignResource.getSlug(), gameMaster));
        LOG.info(
                "campaignCreated: " + campaignCreated.getDetails().getName() + "[" + campaignCreated.getDetails().getSlug() + "]");
        CampaignResource newCampaignResource = CampaignResource.fromCampaignDetails(campaignCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(BASE_PATH + "/{id}").buildAndExpand(newCampaignResource.getSlug()).toUri());

        return new ResponseEntity<CampaignResource>(newCampaignResource, headers, HttpStatus.CREATED);
    }

}
