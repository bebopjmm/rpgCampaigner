package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.dao.CampaignRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.dao.PlayerRepository;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Campaign;
import org.lostkingdomsfrontier.rpgcampaigner.core.domain.Player;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateCampaignEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick Date: 10/6/13 Time: 07:49
 */
public class CampaignEventHandler implements CampaignService {
    private static Logger LOG = LoggerFactory.getLogger(CampaignEventHandler.class);
    private final CampaignRepository campaignRepository;
    private final PlayerRepository playerRepository;

    public CampaignEventHandler(CampaignRepository campaignRepository,
                                PlayerRepository playerRepository) {
        this.campaignRepository = campaignRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public CampaignCreatedEvent createCampaign(CreateCampaignEvent event) {
        LOG.info("createCampaign");
        Player gameMaster = playerRepository.findByUsername(event.getGameMaster().getUsername());
        Campaign campaign = new Campaign();
        campaign.setName(event.getName());
        campaign.setSlug(event.getSlug());
        campaign.setGameMaster(gameMaster);
        campaign = campaignRepository.save(campaign);
        return new CampaignCreatedEvent(Campaign.toCampaignDetails(campaign));
    }

    @Override
    public List<CampaignDetails> getAllCampaigns() {
        LOG.info("getAllCampaigns");
        List<CampaignDetails> results = new ArrayList<>();
        for (Campaign campaign : campaignRepository.findAll()) {
            results.add(Campaign.toCampaignDetails(campaign));
        }
        return results;
    }

    @Override
    public CampaignDetails getCampaignDetails(String campaignSlug) {
        LOG.info("getCampaignDetails(" + campaignSlug + ")");
        Campaign campaign = campaignRepository.findBySlug(campaignSlug);
        if (campaign != null) {
            return Campaign.toCampaignDetails(campaign);
        } else {
            LOG.warn("campaignSlug[" + campaignSlug + "] NOT FOUND in repository");
            return null;
        }
    }
}
