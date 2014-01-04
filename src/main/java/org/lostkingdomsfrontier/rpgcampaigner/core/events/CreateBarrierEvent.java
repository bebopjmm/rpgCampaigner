package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick Date: 10/31/13 Time: 14:32
 */
public class CreateBarrierEvent {
    private final String name;

    private final String description;

    public CreateBarrierEvent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
