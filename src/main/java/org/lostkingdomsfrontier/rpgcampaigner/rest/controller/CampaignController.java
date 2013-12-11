package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PlayerDetails gameMaster = playerService.getPlayerDetails(campaignResource.getGameMasterUsername());
        if (gameMaster == null) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        CampaignCreatedEvent campaignCreated = campaignService.createCampaign(
                new CreateCampaignEvent(campaignResource.getName(), campaignResource.getSlug(), gameMaster));
        LOG.info(
                "campaignCreated: " + campaignCreated.getDetails().getName() + "[" + campaignCreated.getDetails().getSlug() + "]");
        CampaignResource newCampaignResource = CampaignResource.fromCampaignDetails(campaignCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(BASE_PATH + "/{id}").buildAndExpand(newCampaignResource.getSlug()).toUri());

        return new ResponseEntity<>(newCampaignResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CampaignResource> getAllCampaigns() {
        List<CampaignResource> resources = new ArrayList<>();
        for (CampaignDetails details : campaignService.getAllCampaigns()) {
            resources.add(CampaignResource.fromCampaignDetails(details));
        }
        return resources;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{slug}")
    public ResponseEntity<CampaignResource> getCampaign(@PathVariable String slug) {
        CampaignDetails details = campaignService.getCampaignDetails(slug);
        if (details != null) {
            CampaignResource resource = CampaignResource.fromCampaignDetails(details);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
