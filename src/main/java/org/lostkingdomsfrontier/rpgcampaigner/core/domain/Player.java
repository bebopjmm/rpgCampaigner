package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

/**
 * The PlayerResource domain class represents an individual that participates in one or more gaming campaigns. Any
 * account management information would be handled in a separate domain object.
 *
 * @author John McCormick Date: 10/7/13 Time: 15:29
 */
@Entity(name = "PLAYERS")
public class Player {

    @Id
    @Column(name = "PLAYER_ID")
    private String key;


    private String username;

//    private Set<Campaign> campaigns;

    public static PlayerDetails toPlayerDetails(Player player) {
        return new PlayerDetails(player.getUsername());
    }

    public static Player fromPlayerDetails(PlayerDetails details) {
        Player result = new Player();
        result.setUsername(details.getUsername());
        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Campaign> getCampaigns() {
//        return campaigns;
        return new HashSet<>();
    }

    public void setCampaigns(Set<Campaign> campaigns) {
//        this.campaigns = campaigns;
    }
}
