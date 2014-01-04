package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author John McCormick Date: 12/14/13 Time: 14:05
 */
@Document
public class Barrier {
    @Id
    private String key;
    /**
     * Identifier of the {@link Complex} to which this Transition is associated
     */
    private String complexID;

    private boolean isOpen;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComplexID() {
        return complexID;
    }

    public void setComplexID(String complexID) {
        this.complexID = complexID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
