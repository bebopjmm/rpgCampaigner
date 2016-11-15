package com.rpgcampaigner.common;

import java.util.HashSet;
import java.util.Set;

/**
 * An Adjustment captures a singular alteration to a modifiable score (DynamicValue), such as an
 * Ability, Skill, or Save. Any change in the value of the Adjustment triggers a notification event
 * to all subscribed listeners.
 *
 * @author bebopjmm
 * @since 11/15/16
 */
public class Adjustment {

	private AdjustmentCategory category;

	private int modifier;

	Set<AdjustmentListener> subscribers = new HashSet<>();

	public AdjustmentCategory getCategory() {
		return category;
	}

	public void setCategory(AdjustmentCategory category) {
		this.category = category;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
}
