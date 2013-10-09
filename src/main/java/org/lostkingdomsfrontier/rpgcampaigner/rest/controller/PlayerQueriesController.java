package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick
 * Date: 10/9/13 Time: 11:12
 */
@Controller
@RequestMapping("/rpgCampaigner/players")
public class PlayerQueriesController {
    public static final String BASE_PATH = "/rpgCampaigner/players";
    private static Logger LOG = LoggerFactory.getLogger(PlayerQueriesController.class);

    @Autowired
    private PlayerService playerService;

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
