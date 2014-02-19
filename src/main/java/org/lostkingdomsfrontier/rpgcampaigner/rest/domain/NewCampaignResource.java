package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

/**
 * @author John McCormick Date: 10/9/13 Time: 15:18
 */
public class NewCampaignResource {
    private String slug;

    private String name;

    private String gameMasterUsername;

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

    public String getGameMasterUsername() {
        return gameMasterUsername;
    }

    public void setGameMasterUsername(String gameMasterUsername) {
        this.gameMasterUsername = gameMasterUsername;
    }
}
