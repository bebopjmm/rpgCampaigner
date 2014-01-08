/**
 * 
 */
package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick
 *
 */
public class BarrierDetails {
	private final String key;

    /**
     * Barrier description typically shared with the players
     */
    private final String description;
    
    private final boolean isOpen;

	public BarrierDetails(String key, String description, boolean isOpen) {
		super();
		this.key = key;
		this.description = description;
		this.isOpen = isOpen;
	}

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the isOpen
     */
    public boolean isOpen() {
        return isOpen;
    }
    
}
