package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick Date: 10/9/13 Time: 15:21
 */
public class CreateCampaignEvent {
    private final String name;

    private final String slug;

    private final PlayerDetails gameMaster;

    public CreateCampaignEvent(String name, String slug, PlayerDetails gameMaster) {
        this.name = name;
        this.slug = slug;
        this.gameMaster = gameMaster;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public PlayerDetails getGameMaster() {
        return gameMaster;
    }
}
