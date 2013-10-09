package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.CampaignRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Campaign;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author John McCormick
 * Date: 10/6/13 Time: 07:49
 */
public class CampaignEventHandler implements CampaignService {
    private static Logger LOG = LoggerFactory.getLogger(CampaignEventHandler.class);

    private final CampaignRepository campaignRepository;

    public CampaignEventHandler(final CampaignRepository repository) {
        this.campaignRepository = repository;
    }

    @Override
    public CampaignCreatedEvent createCampaign(CampaignDetails details) {
        LOG.info("createCampaign");
        Campaign campaign = Campaign.fromCampaignDetails(details);
        campaign = campaignRepository.save(campaign);
        return new CampaignCreatedEvent(Campaign.toCampaignDetails(campaign));
    }
}
