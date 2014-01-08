package org.lostkingdomsfrontier.rpgcampaigner.rest.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.AreaDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.BarrierDetails;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.ComplexController;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * An AreaResource domain object is a bounded space with a designated game
 * purpose. It may be physically enclosed, like a room, or open, like a wooded
 * glade. Areas are connected by Exits, which may have barriers (e.g, a door).
 * 
 * @author: John McCormick Date: 10/30/13 Time: 18:20
 */
public class AreaResource extends ResourceSupport {
    /**
     * Descriptive title for the area.
     */
    private String            name;

    /**
     * Description typically shared with the players
     */
    private String            description;

    /**
     * Detailed notes typically reserved for GM use
     */
    private String            details;

    /**
     * Set of Exits leading to other Areas.
     */
    private Set<ExitResource> exits = new HashSet<>();

    public static AreaResource fromAreaDetails(AreaDetails details, String campaignSlug) {
        AreaResource resource = new AreaResource();
        resource.setName(details.getName());
        resource.setDescription(details.getDescription());
        resource.setDetails(details.getDetails());
        for (AreaDetails.ExitDetails exitDetails : details.getExitDetails()) {
            resource.getExits().add(ExitResource.fromDetails(exitDetails.getNextAreaID(),
                                                             campaignSlug,
                                                             exitDetails.getBarrier()));
        }

        resource.add(linkTo(ComplexController.class,
                            campaignSlug).slash(details.getComplexID()).slash("areas")
                .slash(details.getKey()).withSelfRel());
        resource.add(linkTo(ComplexController.class,
                            campaignSlug).slash(details.getComplexID()).withRel("complex"));

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

    public Set<ExitResource> getExits() {
        return exits;
    }

    public void setExits(Set<ExitResource> exits) {
        this.exits = exits;
    }

    public static class ExitResource extends ResourceSupport {

        // TODO Add Barrier Information
        private boolean isOpen;

        private String  description;

        public static ExitResource fromDetails(String nextAreaID,
                                               String campaignSlug,
                                               BarrierDetails barrierDetails) {
            ExitResource resource = new ExitResource();
            resource.setDescription(barrierDetails.getDescription());
            resource.setOpen(barrierDetails.isOpen());
            resource.add(linkTo(ComplexController.class,
                                campaignSlug).slash(campaignSlug).slash("areas").slash(nextAreaID)
                    .withRel("nextArea"));
            return resource;
        }

        /**
         * @return the isOpen
         */
        public boolean isOpen() {
            return isOpen;
        }

        /**
         * @param isOpen
         *            the isOpen to set
         */
        public void setOpen(boolean isOpen) {
            this.isOpen = isOpen;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description
         *            the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

    }
}
