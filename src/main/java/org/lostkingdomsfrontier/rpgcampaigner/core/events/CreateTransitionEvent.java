package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author: John McCormick
 * Date: 10/31/13 Time: 14:32
 */
public class CreateTransitionEvent {
    private final String name;

    private final String description;

    public CreateTransitionEvent(String name, String description) {
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
