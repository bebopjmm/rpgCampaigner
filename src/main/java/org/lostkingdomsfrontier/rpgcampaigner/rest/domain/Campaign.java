package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;

import java.util.UUID;

/**
 * @author John McCormick Date: 10/3/13 Time: 14:37
 */
public class Campaign {
    private String slug;

    private String name;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CampaignDetails toCampaignDetails(Campaign campaign) {
        return new CampaignDetails(campaign.getName(), campaign.getSlug());
    }

    public static Campaign fromCampaignDetails(CampaignDetails details) {
        Campaign result = new Campaign();
        result.setName(details.getName());
        result.setSlug(details.getSlug());
        return result;
    }
}
