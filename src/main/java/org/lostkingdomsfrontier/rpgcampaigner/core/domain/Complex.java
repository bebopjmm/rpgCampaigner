package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.hibernate.annotations.*;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * The Complex domain object represents a set of Areas connected by Entrances that can be explored as part of an
 * Adventure.
 *
 * @author John McCormick Date: 10/11/13 Time: 18:21
 */
@Entity(name = "SETTING_COMPLEXES")
public class Complex {
    @Id
    @Column(name = "COMPLEX_ID")
    private String key;
    /**
     * Descriptive title
     */
    private String name;
    /**
     * Slug identifier of Campaign that this Complex is associated with
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CAMPAIGN_ID")
    private Campaign campaign;

    /**
     * The {@link Area} instances that comprise this Complex
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "complex")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<Area> areas = new HashSet<>();

    /**
     * The {@link Barrier} instances used in connections between {@link Area} instances within this Complex
     */
//    @DBRef
//    private Set<Barrier> barriers = new HashSet<>();

    public static ComplexDetails toComplexDetails(Complex complex) {
        return new ComplexDetails(complex.getName(), complex.getKey());
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

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }

    public Set<Barrier> getBarriers() {
//        return barriers;
        return new HashSet<>();
    }

    public void setBarriers(Set<Barrier> barriers) {
//        this.barriers = barriers;
    }

}
