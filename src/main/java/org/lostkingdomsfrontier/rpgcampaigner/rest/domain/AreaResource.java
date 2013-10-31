package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.CampaignController;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.ComplexController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * An AreaResource domain object is a bounded space with a designated game purpose. It may be physically enclosed, like
 * a room, or open, like a wooded glade. Areas are connected by Entrances, which may have barriers (e.g, a door).
 *
 * @author: John McCormick
 * Date: 10/30/13 Time: 18:20
 */
public class AreaResource extends ResourceSupport {
    /**
     * Descriptive title for the area
     */
    private String name;
    /**
     * Area description typically shared with the players
     */
    private String description;
    /**
     * Area detail notes typically reserved for GM use
     */
    private String details;

    public static AreaResource fromAreaDetails(AreaDetails details, String campaignSlug) {
        AreaResource resource = new AreaResource();
        resource.setName(details.getName());
        resource.setDescription(details.getDescription());
        resource.setDetails(details.getDetails());

        resource.add(linkTo(ComplexController.class, campaignSlug).slash(details.getComplexID()).withRel("complex"));

        return resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
