package org.lostkingdomsfrontier.rpgcampaigner.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author: John McCormick Date: 10/24/13 Time: 18:46
 */
@Configuration
@EnableMongoRepositories("org.lostkingdomsfrontier.rpgcampaigner.core.dao")
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "rpg_campaigner";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo();
    }
}
