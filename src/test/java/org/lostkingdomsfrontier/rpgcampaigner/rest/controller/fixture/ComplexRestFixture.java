package org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexCreatedEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 11:52
 */
public class ComplexRestFixture {

    public static final String STANDARD_COMPLEX_SLUG = "catacombs-of-wrath";

    public static ComplexCreatedEvent complexCreated(String complexSlug) {
        return new ComplexCreatedEvent(new ComplexDetails("Test Complex", complexSlug));
    }

    public static String newComplexJSON() {
        return "{\"name\": \"Catacombs of Wrath\", \"slug\": \"rotrl\"}";
    }
}
