package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.BarrierDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: John McCormick Date: 10/31/13 Time: 08:10
 */
public class AreaRestFixture {

    static List<AreaDetails> standardAreas;

    static {
        standardAreas = new ArrayList<>();
        standardAreas.add(new AreaDetails("a1b2c3", "Throne Room", "A grand room", "nothing", "1a2b3c"));
        standardAreas.add(new AreaDetails("d4e5f6", "Transition Hall", "A simple hallway", "nothing", "1a2b3c"));
        BarrierDetails barrierDetails = new BarrierDetails("1a2b3c", "An open archway leads to the next area", true);
        standardAreas.get(0).getExitDetails().add(standardAreas.get(0).new ExitDetails("d4e5f6", barrierDetails));
        standardAreas.get(1).getExitDetails().add(standardAreas.get(1).new ExitDetails("a1b2c3", null));
    }

    public static AreaDetails areaCreated() {
        return new AreaDetails("a1b2c3", "Throne Room", "A grand room", "nothing", "1a2b3c");
    }

    public static String newAreaJSON() {
        return "{\"name\": \"Transition Hall\", \"description\": \"A simple hallway\", \"details\": \"nothing of interest\"}";
    }

    public static List<AreaDetails> allAreas() {
        return standardAreas;
    }

    public static AreaDetails areaFound() {
        return standardAreas.get(0);
    }
    
    public static String areaConnectionJSON() {
        return "{\"barrierDescription\": \"An open archway leads to the next area\", \"isBarrierOpen\": true, \"nextAreaID\": \"d4e5f6\"}";  
    }
    
    /**
     * @return List of two AreaDetail objects that are linked by a common Exit
     */
    public static List<AreaDetails> areasLinkedByExit() {
        List<AreaDetails> result = new ArrayList<>(2);
        result.add(standardAreas.get(0));
        result.add(standardAreas.get(1));
        return result;
    }
}
