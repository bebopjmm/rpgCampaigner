package org.lostkingdomsfrontier.rpgcampaigner.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lostkingdomsfrontier.rpgcampaigner.config.JPATestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.lostkingdomsfrontier.rpgcampaigner.persistence.fixture.JPAAssertions.assertTableExists;
import static org.lostkingdomsfrontier.rpgcampaigner.persistence.fixture.JPAAssertions.assertTableHasColumn;

/**
 * @author John McCormick on 2/26/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPATestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PlayerMappingIntegrationTests {

    @Autowired
    EntityManager manager;

    @Test
    public void thatItemCustomMappingWorks() throws Exception {
        assertTableExists(manager, "PLAYERS");

        assertTableHasColumn(manager, "PLAYERS", "PLAYER_ID");
        assertTableHasColumn(manager, "PLAYERS", "USERNAME");
    }
}
