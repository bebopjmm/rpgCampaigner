package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The Campaign domain object represents a connected series of Adventures conducted over a series of GamingSessions by a
 * group of Players under the direction of a gameMaster.
 *
 * @author John McCormick Date: 10/3/13 Time: 10:00
 */
@Entity(name = "CAMPAIGNS")
public class Campaign {
    @Id
    @Column(name = "CAMPAIGN_ID")
    private String key;

    private String name;

    @Column(unique = true)
    private String slug;

    @OneToOne(cascade = CascadeType.ALL)
    private Player gameMaster;

//    @DBRef
//    private Set<Player> players;

    public static CampaignDetails toCampaignDetails(Campaign campaign) {
        return new CampaignDetails(campaign.getName(), campaign.getSlug());
    }

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

    public Player getGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(Player gameMaster) {
        this.gameMaster = gameMaster;
    }

    public Set<Player> getPlayers() {
//        return players;
        return new HashSet<>();
    }

    public void setPlayers(Set<Player> players) {
//        this.players = players;
    }
}
