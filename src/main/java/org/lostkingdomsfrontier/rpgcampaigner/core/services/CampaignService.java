package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateCampaignEvent;

import java.util.List;

/**
 * @author John McCormick
 * Date: 10/4/13 Time: 09:38
 */
public interface CampaignService {

    public CampaignCreatedEvent createCampaign (CreateCampaignEvent event);

    public List<CampaignDetails> getAllCampaigns();

    public CampaignDetails getCampaignDetails(String campaignSlug);
}
