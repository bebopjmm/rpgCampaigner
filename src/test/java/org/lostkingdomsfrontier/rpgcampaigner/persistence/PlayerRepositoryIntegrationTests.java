package org.lostkingdomsfrontier.rpgcampaigner.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lostkingdomsfrontier.rpgcampaigner.config.JPATestConfig;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.CampaignRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.PlayerRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Campaign;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static junit.framework.TestCase.*;

/**
 * @author John McCormick on 2/27/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPATestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PlayerRepositoryIntegrationTests {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {
        String key = UUID.randomUUID().toString();

        Player player = new Player();
        player.setKey(key);
        player.setUsername("sharalahur");

        playerRepository.save(player);

        Player retrievedPlayer = playerRepository.findByUsername("sharalahur");
        assertNotNull(retrievedPlayer);
        assertEquals(key, retrievedPlayer.getKey());
        assertNotNull(retrievedPlayer.getCampaigns());
        assertTrue(retrievedPlayer.getCampaigns().isEmpty());
        assertEquals("sharalahur", retrievedPlayer.getUsername());
    }

    @Test
    public void thatCampaignAddedToPlayerWorks() throws Exception {
        String playerKey = UUID.randomUUID().toString();
        String campaignKey = UUID.randomUUID().toString();

        Player player = new Player();
        player.setKey(playerKey);
        player.setUsername("sharalahur");

        player = playerRepository.save(player);

        Campaign campaign = new Campaign();
        campaign.setKey(campaignKey);
        campaign.setName("Rise of the Runelords");
        campaign.setSlug("rotrl");

        campaign = campaignRepository.save(campaign);

        player.getCampaigns().add(campaign);
        player = playerRepository.save(player);
        assertNotNull(player.getCampaigns());
        assertFalse(player.getCampaigns().isEmpty());
        assertTrue(player.getCampaigns().contains(campaign));
    }

}
