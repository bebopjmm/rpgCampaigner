package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.TransitionDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author John McCormick Date: 10/11/13 Time: 18:18
 */
@Document
public class Transition {
    @Id
    private String key;
    /**
     * Identifier of the {@link Complex} to which this Transition is associated
     */
    private String complexID;
    /**
     * Descriptive title for the area
     */
    private String name;
    /**
     * Transition description typically shared with the players
     */
    private String description;

    public static TransitionDetails toTransitionDetails(Transition transition) {
        return new TransitionDetails(transition.getKey(), transition.getName(), transition.getDescription(),
                                     transition.getComplexID());
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

    public String getComplexID() {
        return complexID;
    }

    public void setComplexID(String complexID) {
        this.complexID = complexID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
