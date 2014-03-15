package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * An AreaPortal defines the connection allowing passage between two {@link Area} objects.
 * The connection may be bi-directional or one-way. The passage may be restricted by a Barrier.
 *
 * @author John McCormick  on 3/6/14.
 */
@Entity(name = "SETTING_AREA_PORTALS")
public class AreaPortal {

    @Id
    @Column(name = "AREA_PORTAL_ID")
    private String key;

    /**
     * Descriptive title for the AreaPortal
     */
    private String name;

    private boolean isBidirectional;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="AREAPORTAL_AREA",
            joinColumns={@JoinColumn(name="PORTAL_ID", referencedColumnName="AREA_PORTAL_ID")},
            inverseJoinColumns={@JoinColumn(name="AREA_ID", referencedColumnName="AREA_ID")})
    private Set<Area> connectedAreas = new HashSet<>();
//    /**
//     *
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Cascade({CascadeType.SAVE_UPDATE})
//    @JoinColumn(name = "AREA_LEFT_ID")
//    private Area areaLeft;
//
//    /**
//     *
//     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Cascade({CascadeType.SAVE_UPDATE})
//    @JoinColumn(name = "AREA_RIGHT_ID")
//    private Area areaRight;

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

    public boolean isBidirectional() {
        return isBidirectional;
    }

    public void setBidirectional(boolean isBidirectional) {
        this.isBidirectional = isBidirectional;
    }

    public Set<Area> getConnectedAreas() {
        return connectedAreas;
    }

    public void setConnectedAreas(Set<Area> connectedAreas) {
        this.connectedAreas = connectedAreas;
    }
}
