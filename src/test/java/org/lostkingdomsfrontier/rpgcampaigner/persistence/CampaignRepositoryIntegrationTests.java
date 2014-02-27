package org.lostkingdomsfrontier.rpgcampaigner.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lostkingdomsfrontier.rpgcampaigner.config.JPATestConfig;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.CampaignRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author John McCormick on 2/27/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPATestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CampaignRepositoryIntegrationTests {

    @Autowired
    CampaignRepository campaignRepository;

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {
        String key = UUID.randomUUID().toString();

        Campaign campaign = new Campaign();
        campaign.setKey(key);
        campaign.setName("Rise of the Runelords");
        campaign.setSlug("rotrl");

        campaignRepository.save(campaign);

        Campaign retrievedCampaign = campaignRepository.findBySlug("rotrl");

        assertNotNull(retrievedCampaign);
        assertEquals(key, retrievedCampaign.getKey());
        assertEquals("rotrl", retrievedCampaign.getSlug());
        assertEquals("Rise of the Runelords", retrievedCampaign.getName());
    }

}
