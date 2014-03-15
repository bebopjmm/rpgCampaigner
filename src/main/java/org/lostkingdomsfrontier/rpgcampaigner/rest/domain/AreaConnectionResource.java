/**
 * 
 */
package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import java.io.Serializable;


/**
 * @author John McCormick
 *
 */
public class AreaConnectionResource implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String barrierDescription;
    
    private boolean isBarrierOpen;

    private String nextAreaID;

    private boolean isBidirectional;

    /**
     * @return the barrierDescription
     */
    public String getBarrierDescription() {
        return barrierDescription;
    }

    /**
     * @param barrierDescription the barrierDescription to set
     */
    public void setBarrierDescription(String barrierDescription) {
        this.barrierDescription = barrierDescription;
    }

    public boolean isBarrierOpen() {
        return isBarrierOpen;
    }

    public void setBarrierOpen(boolean isBarrierOpen) {
        this.isBarrierOpen = isBarrierOpen;
    }

    /**
     * @return the nextAreaID
     */
    public String getNextAreaID() {
        return nextAreaID;
    }

    /**
     * @param nextAreaID the nextAreaID to set
     */
    public void setNextAreaID(String nextAreaID) {
        this.nextAreaID = nextAreaID;
    }

    public boolean isBidirectional() {
        return isBidirectional;
    }

    public void setBidirectional(boolean isBidirectional) {
        this.isBidirectional = isBidirectional;
    }
}
