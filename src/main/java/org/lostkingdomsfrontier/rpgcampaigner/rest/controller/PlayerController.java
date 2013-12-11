package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.PlayerResource;
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
 * The PlayerController coordinates the interactions between the RESTful commands and the PlayerService.
 *
 * @author John McCormick Date: 10/8/13 Time: 17:37
 */
@Controller
@RequestMapping(PlayerController.BASE_PATH)
public class PlayerController {
    public static final String BASE_PATH = "/rpgCampaigner/players";
    private static Logger LOG = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PlayerResource> createPlayer(@RequestBody PlayerResource playerResource,
                                                       UriComponentsBuilder builder) {
        LOG.info("createPlayer for " + playerResource.getUserName());

        PlayerCreatedEvent playerCreated = playerService.createPlayer(PlayerResource.toPlayerDetails(playerResource));
        LOG.info("playerCreated: " + playerCreated.getDetails().getUsername());
        PlayerResource newPlayerResource = PlayerResource.fromPlayerDetails(playerCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(BASE_PATH + "/{id}").buildAndExpand(newPlayerResource.getUserName()).toUri());

        return new ResponseEntity<PlayerResource>(newPlayerResource, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PlayerResource> getAllPlayers() {
        List<PlayerResource> playerResources = new ArrayList<>();
        for (PlayerDetails details : playerService.getAllPlayerDetails()) {
            playerResources.add(PlayerResource.fromPlayerDetails(details));
        }
        return playerResources;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public ResponseEntity<PlayerResource> getPlayer(@PathVariable String username) {
        PlayerDetails details = playerService.getPlayerDetails(username);
        if (details != null) {
            PlayerResource playerResource = PlayerResource.fromPlayerDetails(details);
            return new ResponseEntity<PlayerResource>(playerResource, HttpStatus.OK);
        } else {
            return new ResponseEntity<PlayerResource>(HttpStatus.NOT_FOUND);
        }
    }

}
