package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * An Area domain object is a bounded space with a designated game purpose. It
 * may be physically enclosed, like a room, or open, like a wooded glade. Areas
 * are connected by Exit objects, which may include a Barrier (e.g, a door).
 * 
 * @author John McCormick Date: 10/11/13 Time: 15:17
 */
@Document
public class Area {

    @Id
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
    private String    complexID;

    /**
     * Set of {@link Exit} leading to other Areas
     */
    private Set<Exit> exits = new HashSet<>();

    public static AreaDetails toAreaDetails(Area area) {
        AreaDetails details = new AreaDetails(area.getKey(), area.getName(), area.getDescription(),
                area.getDetails(), area.getComplexID());
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

    public String getComplexID() {
        return complexID;
    }

    public void setComplexID(String complexID) {
        this.complexID = complexID;
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
        return exits;
    }

    public void setExits(Set<Exit> exits) {
        this.exits = exits;
    }

    public static class Exit {
        @DBRef
        private Area    nextArea;

        @DBRef
        private Barrier barrier;

        public Area getNextArea() {
            return nextArea;
        }

        public void setNextArea(Area nextArea) {
            this.nextArea = nextArea;
        }

        public Barrier getBarrier() {
            return barrier;
        }

        public void setBarrier(Barrier barrier) {
            this.barrier = barrier;
        }

    }
}
