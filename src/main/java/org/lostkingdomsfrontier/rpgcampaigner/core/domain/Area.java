package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * An Area domain object is a bounded space with a designated game purpose. It may be physically enclosed, like a room,
 * or open, like a wooded glade. Areas are connected by Entrances, which may have barriers (e.g, a door).
 *
 * @author John McCormick Date: 10/11/13 Time: 15:17
 */
@Document
public class Area {
    @Id
    private String key;
    /**
     * Descriptive title for the area
     */
    private String name;
    /**
     * Readable identifier used to reference this area
     */
    @Indexed(unique = true)
    private String slug;
    /**
     * Area description typically shared with the players
     */
    private String description;
    /**
     * Area detail notes typically reserved for GM use
     */
    private String details;

    List<Entrance> entrances = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
