package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;

/**
 * @author: John McCormick
 * Date: 10/31/13 Time: 08:10
 */
public class AreaRestFixture {


    public static AreaDetails areaCreated() {
        return new AreaDetails("a1b2c3", "Throne Room", "A grand room", "nothing", "1a2b3c");
    }

    public static String newAreaJSON() {
        return "{\"name\": \"Entrance Hall\", \"description\": \"A simple hallway\", \"details\": \"nothing of interest\"}";
    }
}
