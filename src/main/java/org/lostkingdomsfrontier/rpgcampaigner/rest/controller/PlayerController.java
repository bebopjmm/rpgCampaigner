package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.Player;
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
 * @author John McCormick
 * Date: 10/8/13 Time: 17:37
 */
@Controller
@RequestMapping("/rpgCampaigner/players")
public class PlayerController {
    public static final String BASE_PATH = "/rpgCampaigner/players";
    private static Logger LOG = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Player> createPlayer(@RequestBody Player player, UriComponentsBuilder builder) {
        LOG.info("createPlayer for " + player.getUserName());

        PlayerCreatedEvent playerCreated = playerService.createPlayer(Player.toPlayerDetails(player));
        LOG.info("playerCreated: " + playerCreated.getDetails().getUsername());
        Player newPlayer = Player.fromPlayerDetails(playerCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(BASE_PATH + "/{id}").buildAndExpand(newPlayer.getUserName()).toUri());

        return new ResponseEntity<Player>(newPlayer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        for (PlayerDetails details : playerService.getAllPlayerDetails()) {
            players.add(Player.fromPlayerDetails(details));
        }
        return players;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public ResponseEntity<Player> getPlayer(@PathVariable String username) {
        PlayerDetails details = playerService.getPlayerDetails(username);
        if (details != null) {
            Player player = Player.fromPlayerDetails(details);
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }
    }

}
