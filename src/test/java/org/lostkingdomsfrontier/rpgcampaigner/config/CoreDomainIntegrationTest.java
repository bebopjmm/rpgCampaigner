package org.lostkingdomsfrontier.rpgcampaigner.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.PlayerRestFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author John McCormick
 * Date: 10/24/13 Time: 18:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class, MongoTestConfig.class})
public class CoreDomainIntegrationTest {

    @Autowired
    PlayerService playerService;

    @Test
    public void addNewPlayer() throws Exception {
        PlayerDetails details = PlayerRestFixture.customPlayerDetails("fred");
        playerService.createPlayer(details);

        List<PlayerDetails> allPlayers = playerService.getAllPlayerDetails();
        assertEquals(1, allPlayers.size());
    }
}
