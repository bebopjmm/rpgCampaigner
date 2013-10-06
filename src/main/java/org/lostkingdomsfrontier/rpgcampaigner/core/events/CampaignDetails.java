package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: bebopjmm Date: 10/4/13 Time: 09:58
 */
public class CampaignDetails {
    private final String name;

    private final String slug;

    public CampaignDetails(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
