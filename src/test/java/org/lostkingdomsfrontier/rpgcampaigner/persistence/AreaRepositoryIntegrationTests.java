package org.lostkingdomsfrontier.rpgcampaigner.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lostkingdomsfrontier.rpgcampaigner.config.JPATestConfig;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.AreaRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Area;
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
public class AreaRepositoryIntegrationTests {

    @Autowired
    AreaRepository areaRepository;

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {
        String key = UUID.randomUUID().toString();

        Area area = new Area();
        area.setKey(key);
        area.setName("Throne Room");
        area.setDescription("You enter a grand throne room");
        area.setDetails("Nothing of interest");

        areaRepository.save(area);

        Area retrievedArea = areaRepository.findOne(key);
        assertNotNull(retrievedArea);
        assertEquals(key, retrievedArea.getKey());
        assertEquals("Throne Room", retrievedArea.getName());
        assertEquals("You enter a grand throne room", retrievedArea.getDescription());
        assertEquals("Nothing of interest", retrievedArea.getDetails());
    }
}
