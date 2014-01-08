package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick Date: 12/12/13 Time: 17:30
 */
public class LinkAreasEvent {
    private final String areaID1;

    private final String areaID2;

    private CreateBarrierEvent createBarrierEvent;

    public LinkAreasEvent(String areaID1, String areaID2, CreateBarrierEvent createBarrierEvent) {
        this.areaID1 = areaID1;
        this.areaID2 = areaID2;
        this.createBarrierEvent = createBarrierEvent;
    }

    public String getAreaID1() {
        return areaID1;
    }

    public String getAreaID2() {
        return areaID2;
    }

    public CreateBarrierEvent getCreateBarrierEvent() {
        return createBarrierEvent;
    }
}
