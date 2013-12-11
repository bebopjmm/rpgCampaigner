package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: John McCormick Date: 10/30/13 Time: 18:11
 */
public class CreateAreaEvent {
    private final String areaName;

    private final String areaDescription;

    private final String areaDetails;

    public CreateAreaEvent(String areaName, String areaDescription, String areaDetails) {
        this.areaName = areaName;
        this.areaDescription = areaDescription;
        this.areaDetails = areaDetails;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public String getAreaDetails() {
        return areaDetails;
    }
}
