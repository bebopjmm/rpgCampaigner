package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: John McCormick Date: 10/31/13 Time: 14:23
 */
public class TransitionDetails {
    private final String key;
    /**
     * Descriptive title for the transition
     */
    private final String name;
    /**
     * Transition description typically shared with the players
     */
    private final String description;

    private final String complexID;

    public TransitionDetails(String key, String name, String description, String complexID) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.complexID = complexID;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getComplexID() {
        return complexID;
    }
}
