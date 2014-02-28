package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.hibernate.annotations.*;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * An Area domain object is a bounded space with a designated game purpose. It
 * may be physically enclosed, like a room, or open, like a wooded glade. Areas
 * are connected by Exit objects, which may include a Barrier (e.g, a door).
 * 
 * @author John McCormick Date: 10/11/13 Time: 15:17
 */
@Entity(name = "SETTING_AREAS")
public class Area {

    @Id
    @Column(name = "AREA_ID")
    private String    key;
    /**
     * Descriptive title for the area
     */
    private String    name;
    /**
     * Area description typically shared with the players
     */
    private String    description;
    /**
     * Area detail notes typically reserved for GM use
     */
    private String    details;
    /**
     * Identifier of the {@link Complex} to which this Area is associated
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="COMPLEX_ID")
    private Complex    complex;

    /**
     * Set of {@link Exit} leading to other Areas
     */
    @Transient
    private Set<Exit> exits = new HashSet<>();

    public static AreaDetails toAreaDetails(Area area) {
        AreaDetails details = new AreaDetails(area.getKey(), area.getName(), area.getDescription(),
                area.getDetails(), area.getComplex().getKey());
        for (Exit exit : area.getExits()) {
            AreaDetails.ExitDetails exitDetails = details.new ExitDetails(exit.getNextArea().getKey(),
                                                                          Barrier.toDetails(exit.getBarrier()));
            details.getExitDetails().add(exitDetails);
        }
        return details;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Exit> getExits() {
        return new HashSet<>();
    }

    public void setExits(Set<Exit> exits) {
        this.exits = exits;
    }

    public static class Exit {

        private Area    nextArea;

//        @DBRef
//        private Barrier barrier;

        public Area getNextArea() {
            return nextArea;
        }

        public void setNextArea(Area nextArea) {
            this.nextArea = nextArea;
        }

        public Barrier getBarrier() {
//            return barrier;
            return null;
        }

        public void setBarrier(Barrier barrier) {
//            this.barrier = barrier;
        }

    }
}
