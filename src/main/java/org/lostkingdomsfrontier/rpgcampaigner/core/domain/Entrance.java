package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author John McCormick
 * Date: 10/11/13 Time: 18:18
 */
@Document
public class Entrance {
    @Id
    private String key;
    /**
     * Descriptive title for the area
     */
    private String name;
    /**
     * Readable identifier used to reference this entrance
     */
    @Indexed(unique = true)
    private String slug;
    /**
     * Entrance description typically shared with the players
     */
    private String description;

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
}
