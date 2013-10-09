package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * The Player domain class represents an individual that participates in one or more gaming campaigns. Any account
 * management information would be handled in a separate domain object.
 *
 * @author John McCormick
 * Date: 10/7/13 Time: 15:29
 */
@Document
public class Player {

    @Id
    private String key;
    @Indexed(unique = true)
    private String userName;
    @DBRef
    private Set<Campaign> campaigns;

    public static PlayerDetails toPlayerDetails(Player player) {
        return new PlayerDetails(player.getUserName());
    }

    public static Player fromPlayerDetails(PlayerDetails details) {
        Player result = new Player();
        result.setUserName(details.getUsername());
        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
