package org.lostkingdomsfrontier.rpgcampaigner.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lostkingdomsfrontier.rpgcampaigner.config.JPATestConfig;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.ComplexRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Complex;
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
public class ComplexRepositoryIntegrationTests {

    @Autowired
    ComplexRepository complexRepository;

    @Test
    public void thatItemIsInsertedIntoRepoWorks() throws Exception {
        String key = UUID.randomUUID().toString();

        Complex complex = new Complex();
        complex.setKey(key);
        complex.setName("Sandpoint Glassworks");

        complexRepository.save(complex);

        Complex retrievedComplex = complexRepository.findOne(key);
        assertNotNull(retrievedComplex);
        assertEquals(key, retrievedComplex.getKey());
        assertEquals("Sandpoint Glassworks", retrievedComplex.getName());
    }

}
