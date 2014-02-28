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
 * @author John McCormick on 2/27/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPATestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AreaMappingIntegrationTests {

    @Autowired
    EntityManager manager;

    @Test
    public void thatItemCustomMappingWorks() throws Exception {
        assertTableExists(manager, "SETTING_AREAS");

        assertTableHasColumn(manager, "SETTING_AREAS", "AREA_ID");
        assertTableHasColumn(manager, "SETTING_AREAS", "NAME");
        assertTableHasColumn(manager, "SETTING_AREAS", "DESCRIPTION");
        assertTableHasColumn(manager, "SETTING_AREAS", "DETAILS");
        assertTableHasColumn(manager, "SETTING_AREAS", "COMPLEX_ID");
    }
}
