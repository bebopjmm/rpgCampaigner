package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * The Campaign domain object represents a connected series of Adventures conducted of a series of GamingSessions by a
 * group of Players under the direction of a gameMaster.
 *
 * @author John McCormick
 * Date: 10/3/13 Time: 10:00
 */
@Document
public class Campaign {
    @Id
    private String key;
    private String name;
    @Indexed(unique = true)
    private String slug;
    @DBRef
    private Player gameMaster;
    @DBRef
    private Set<Player> players;

    public static CampaignDetails toCampaignDetails(Campaign campaign) {
        return new CampaignDetails(campaign.getName(), campaign.getSlug());
    }

    public static Campaign fromCampaignDetails(CampaignDetails details) {
        Campaign result = new Campaign();
        result.setName(details.getName());
        result.setSlug(details.getSlug());
        return result;
    }

    public String getKey() {
        return key;
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

    public Player getGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(Player gameMaster) {
        this.gameMaster = gameMaster;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
