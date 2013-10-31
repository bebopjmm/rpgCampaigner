package org.lostkingdomsfrontier.rpgcampaigner.config;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.AreaRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.CampaignRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.ComplexRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.PlayerRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author John McCormick
 * Date: 10/24/13 Time: 18:15
 */
@Configuration
public class ServiceConfig {

    @Bean
    public CampaignService createCampaignService(CampaignRepository campaignRepository,
                                                 PlayerRepository playerRepository) {
        return new CampaignEventHandler(campaignRepository, playerRepository);
    }

    @Bean
    public PlayerService createPlayerService(PlayerRepository playerRepository) {
        return new PlayerEventHandler(playerRepository);
    }

    @Bean
    public ComplexService createComplexService(ComplexRepository complexRepository, AreaRepository areaRepository) {
        return new ComplexEventHandler(complexRepository, areaRepository);
    }

}
