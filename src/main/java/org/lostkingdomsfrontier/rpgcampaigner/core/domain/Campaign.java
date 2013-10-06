package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author John McCormick Date: 10/3/13 Time: 10:00
 */
@Document
public class Campaign {
    @Id
    private String key;

    private String name;

    private String slug;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
