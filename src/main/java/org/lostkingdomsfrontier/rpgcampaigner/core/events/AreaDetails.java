package org.lostkingdomsfrontier.rpgcampaigner.core.events;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: John McCormick Date: 10/30/13 Time: 18:16
 */
public class AreaDetails {
    private final String key;
    /**
     * Descriptive title for the area
     */
    private final String name;
    /**
     * Area description typically shared with the players
     */
    private final String description;
    /**
     * Area detail notes typically reserved for GM use
     */
    private final String details;

    private final String complexID;

    private Set<ExitDetails> exitDetails = new HashSet<>();

    public AreaDetails(String key, String name, String description, String details, String complexID) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.details = details;
        this.complexID = complexID;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }

    public String getComplexID() { return complexID; }

    public Set<ExitDetails> getExitDetails() { return exitDetails; };

    public class ExitDetails {
        private final String nextAreaID;
        
        private final BarrierDetails barrier;

        public ExitDetails(String nextAreaID, BarrierDetails barrier) {
            assert (barrier != null);
            this.nextAreaID = nextAreaID;
            this.barrier = barrier;
        }

        public String getNextAreaID() {
            return nextAreaID;
        }

        /**
         * @return the barrier
         */
        public BarrierDetails getBarrier() {
            return barrier;
        }
    }
}




