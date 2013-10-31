package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: John McCormick
 * Date: 10/30/13 Time: 18:16
 */
public class AreaDetails {
    private final String key;
    /**
     * Descriptive title for the area
     */
    private final String name;
    /**
     * Area description typically shared with the players
     */
    private final String description;
    /**
     * Area detail notes typically reserved for GM use
     */
    private final String details;

    private final String complexID;


    public AreaDetails(String key, String name, String description, String details, String complexID) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public String getComplexID() {
        return complexID;
    }
}
