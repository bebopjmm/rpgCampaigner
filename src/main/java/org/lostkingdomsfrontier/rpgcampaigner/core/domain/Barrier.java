package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.BarrierDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author John McCormick Date: 12/14/13 Time: 14:05
 */
@Document
public class Barrier {
    @Id
    private String  key;

    private boolean isOpen;

    private String  description;

    public static BarrierDetails toDetails(Barrier barrier) {
        return new BarrierDetails(barrier.getKey(), barrier.getDescription(), barrier.isOpen());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
