package org.lostkingdomsfrontier.rpgcampaigner.core.events;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 10:58
 */
public class ComplexDetails {
    private final String name;

    private final String slug;

    public ComplexDetails(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
