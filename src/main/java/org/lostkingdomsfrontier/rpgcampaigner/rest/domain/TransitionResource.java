package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.TransitionDetails;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.ComplexController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author: John McCormick Date: 10/31/13 Time: 14:17
 */
public class TransitionResource extends ResourceSupport {
    /**
     * Descriptive title for the transition
     */
    private String name;
    /**
     * Transition description typically shared with the players
     */
    private String description;

    public static TransitionResource fromTransitionDetails(TransitionDetails details, String campaignSlug) {
        TransitionResource resource = new TransitionResource();
        resource.setName(details.getName());
        resource.setDescription(details.getDescription());

        resource.add(linkTo(ComplexController.class, campaignSlug).slash(details.getComplexID()).slash("transitions")
                             .slash(details.getKey()).withSelfRel());
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
}
