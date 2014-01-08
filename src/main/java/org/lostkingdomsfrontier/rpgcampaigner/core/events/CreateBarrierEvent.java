package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick Date: 10/31/13 Time: 14:32
 */
public class CreateBarrierEvent {
    private final boolean isOpen;

    private final String description;

    public CreateBarrierEvent(String description, boolean isOpen) {
        this.description = description;
        this.isOpen = isOpen;
    }

    public String getDescription() {
        return description;
    }

	public boolean isOpen() {
		return isOpen;
	}
    
}
