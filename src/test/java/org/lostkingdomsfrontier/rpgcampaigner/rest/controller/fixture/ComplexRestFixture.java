package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 11:52
 */
public class ComplexRestFixture {

    public static final String STANDARD_COMPLEX_KEY = "1a2b3c4d";

    static List<ComplexDetails> standardComplexes;

    static {
        standardComplexes = new ArrayList<>();
        standardComplexes.add(customComplexDetails("a1b2c3d4", "Glassworks"));
        standardComplexes.add(customComplexDetails(STANDARD_COMPLEX_KEY, "Catacombs of Wrath"));
    }

    public static ComplexDetails customComplexDetails(String complexKey, String complexName) {
        return new ComplexDetails(complexName, complexKey);
    }
    public static ComplexCreatedEvent complexCreated(String complexKey, String complexName) {
        return new ComplexCreatedEvent(customComplexDetails(complexKey, complexName));
    }

    public static List<ComplexDetails> getStandardComplexes() {
        return standardComplexes;
    }

    public static ComplexDetails complexFound(String key) {
        return customComplexDetails(key, "Glassworks");
    }

    public static String newComplexJSON() {
        return "{\"name\": \"Catacombs of Wrath\"}";
    }
}
