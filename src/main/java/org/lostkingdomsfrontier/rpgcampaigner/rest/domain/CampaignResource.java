package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.CampaignController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * The CampaignResource domain object represents a connected series of Adventures conducted of a series of GamingSessions by a
 * group of Players under the direction of a gameMaster.
 *
 * @author John McCormick
 * Date: 10/3/13 Time: 14:37
 */
public class CampaignResource extends ResourceSupport {
    private String slug;

    private String name;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CampaignDetails toCampaignDetails(CampaignResource campaignResource) {
        return new CampaignDetails(campaignResource.getName(), campaignResource.getSlug());
    }

    public static CampaignResource fromCampaignDetails(CampaignDetails details) {
        CampaignResource result = new CampaignResource();
        result.setName(details.getName());
        result.setSlug(details.getSlug());

        result.add(linkTo(CampaignController.class).slash(result.getSlug()).withSelfRel());
        return result;
    }
}
