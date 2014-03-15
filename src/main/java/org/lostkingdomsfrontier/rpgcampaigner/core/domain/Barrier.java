package org.lostkingdomsfrontier.rpgcampaigner.core.domain;

import org.lostkingdomsfrontier.rpgcampaigner.core.events.BarrierDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author John McCormick Date: 12/14/13 Time: 14:05
 */
@Entity(name = "SETTING_BARRIERS")
public class Barrier {
    @Id
    @Column(name = "BARRIER_ID")
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
