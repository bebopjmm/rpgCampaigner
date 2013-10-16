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

    public static final String STANDARD_COMPLEX_SLUG = "catacombs-of-wrath";

    static List<ComplexDetails> standardComplexes;

    static {
        standardComplexes = new ArrayList<>();
        standardComplexes.add(customComplexDetails("glassworks"));
        standardComplexes.add(customComplexDetails(STANDARD_COMPLEX_SLUG));
    }

    public static ComplexDetails customComplexDetails(String complexSlug) {
        return new ComplexDetails("Test Complex", complexSlug);
    }
    public static ComplexCreatedEvent complexCreated(String complexSlug) {
        return new ComplexCreatedEvent(customComplexDetails(complexSlug));
    }

    public static List<ComplexDetails> getStandardComplexes() {
        return standardComplexes;
    }

    public static ComplexDetails complexFound(String slug) {
        return customComplexDetails(slug);
    }

    public static String newComplexJSON() {
        return "{\"name\": \"Catacombs of Wrath\", \"slug\": \"rotrl\"}";
    }
}
