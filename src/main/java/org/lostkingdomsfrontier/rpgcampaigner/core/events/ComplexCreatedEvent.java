package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick Date: 10/15/13 Time: 11:05
 */
public class ComplexCreatedEvent {
    private final ComplexDetails details;

    public ComplexCreatedEvent(ComplexDetails details) {
        this.details = details;
    }

    public ComplexDetails getDetails() {
        return details;
    }
}
