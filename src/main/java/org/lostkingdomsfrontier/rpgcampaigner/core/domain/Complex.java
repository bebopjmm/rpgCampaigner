package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * @author John McCormick
 * Date: 10/11/13 Time: 18:21
 */
@Document
public class Complex {
    @Id
    private String key;
    /**
     * Descriptive title
     */
    private String name;
    /**
     * Readable identifier used to reference this complex
     */
    @Indexed(unique = true)
    private String slug;

    private String campaignSlug;

    @DBRef
    private Set<Area> areas = new HashSet<>();
    @DBRef
    private Set<Entrance> entrances = new HashSet<>();

    public static ComplexDetails toComplexDetails(Complex complex) {
        return new ComplexDetails(complex.getName(), complex.getSlug());
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

    public String getCampaignSlug() {
        return campaignSlug;
    }

    public void setCampaignSlug(String campaignSlug) {
        this.campaignSlug = campaignSlug;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }

    public Set<Entrance> getEntrances() {
        return entrances;
    }

    public void setEntrances(Set<Entrance> entrances) {
        this.entrances = entrances;
    }
}
