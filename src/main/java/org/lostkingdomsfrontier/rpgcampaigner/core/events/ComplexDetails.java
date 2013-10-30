package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 10:58
 */
public class ComplexDetails {
    private final String name;

    private final String key;

    public ComplexDetails(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
