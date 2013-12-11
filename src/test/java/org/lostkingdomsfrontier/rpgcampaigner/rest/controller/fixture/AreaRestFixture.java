package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;

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
}
