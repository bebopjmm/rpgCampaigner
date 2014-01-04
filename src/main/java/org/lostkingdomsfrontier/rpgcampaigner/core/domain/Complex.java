package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * The Complex domain object represents a set of Areas connected by Entrances that can be explored as part of an
 * Adventure.
 *
 * @author John McCormick Date: 10/11/13 Time: 18:21
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
     * Slug identifier of Campaign that this Complex is associated with
     */
    private String campaignSlug;

    /**
     * The {@link Area} instances that comprise this Complex
     */
    @DBRef
    private Set<Area> areas = new HashSet<>();
    /**
     * The {@link Barrier} instances used in connections between {@link Area} instances within this Complex
     */
    @DBRef
    private Set<Barrier> barriers = new HashSet<>();

    public static ComplexDetails toComplexDetails(Complex complex) {
        return new ComplexDetails(complex.getName(), complex.getKey());
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

    public Set<Barrier> getBarriers() {
        return barriers;
    }

    public void setBarriers(Set<Barrier> barriers) {
        this.barriers = barriers;
    }

}
