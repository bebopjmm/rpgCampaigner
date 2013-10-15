package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.CampaignController;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.ComplexController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The ComplexResource domain object represents a set of Areas connected by Entrances that can be explored as part of an
 * Adventure.
 *
 * @author John McCormick
 * Date: 10/15/13 Time: 10:24
 */
public class ComplexResource extends ResourceSupport {
    /**
     * Descriptive title
     */
    private String name;
    /**
     * Readable identifier used to reference this complex
     */
    private String slug;

    public static ComplexResource fromComplexDetails(String campaignSlug, ComplexDetails details) {
        ComplexResource resource = new ComplexResource();
        resource.setName(details.getName());
        resource.setSlug(details.getSlug());

        resource.add(linkTo(ComplexController.class, campaignSlug).slash(details.getSlug()).withSelfRel());
        resource.add(linkTo(CampaignController.class).slash(campaignSlug).withRel("campaign"));

        return resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
