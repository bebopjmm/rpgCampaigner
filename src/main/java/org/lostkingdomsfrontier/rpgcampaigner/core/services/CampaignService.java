package org.lostkingdomsfrontier.rpgcampaigner.core.services;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;

/**
 * @author: bebopjmm Date: 10/4/13 Time: 09:38
 */
public interface CampaignService {

    public CampaignCreatedEvent createCampaign (CampaignDetails details);
}
