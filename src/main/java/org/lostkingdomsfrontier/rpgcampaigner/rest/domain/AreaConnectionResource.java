/**
 * 
 */
package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.springframework.hateoas.ResourceSupport;


/**
 * @author John McCormick
 *
 */
public class AreaConnectionResource extends ResourceSupport {
    private String areaID1;
    
    private String areaID2;
    
    private String barrierDescription;
    
    private boolean isBarrierOpen;

    /**
     * @return the areaID1
     */
    public String getAreaID1() {
        return areaID1;
    }

    /**
     * @param areaID1 the areaID1 to set
     */
    public void setAreaID1(String areaID1) {
        this.areaID1 = areaID1;
    }

    /**
     * @return the areaID2
     */
    public String getAreaID2() {
        return areaID2;
    }

    /**
     * @param areaID2 the areaID2 to set
     */
    public void setAreaID2(String areaID2) {
        this.areaID2 = areaID2;
    }

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

    /**
     * @return the isBarrierOpen
     */
    public boolean isBarrierOpen() {
        return isBarrierOpen;
    }

    /**
     * @param isBarrierOpen the isBarrierOpen to set
     */
    public void setBarrierOpen(boolean isBarrierOpen) {
        this.isBarrierOpen = isBarrierOpen;
    }
    
    
}
