package org.lostkingdomsfrontier.rpgcampaigner.config;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.CampaignRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.PlayerRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.CampaignEventHandler;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.CampaignService;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerEventHandler;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author John McCormick
 * Date: 10/24/13 Time: 18:15
 */
@Configuration
public class CoreTestConfig {

    @Bean
    public CampaignService createCampaignService(CampaignRepository campaignRepository,
                                                 PlayerRepository playerRepository) {
        return new CampaignEventHandler(campaignRepository, playerRepository);
    }

    @Bean
    public PlayerService createPlayerService(PlayerRepository playerRepository) {
        return new PlayerEventHandler(playerRepository);
    }

}
