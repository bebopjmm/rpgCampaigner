package com.rpgcampaigner.common;

import java.util.HashSet;
import java.util.Set;

/**
 * An Modifier captures a singular alteration to a modifiable score (DynamicValue), such as an
 * Ability, Skill, or Save. Any change in the value of the Modifier triggers a notification event
 * to all subscribed listeners.
 *
 * @author bebopjmm
 * @since 11/15/16
 */
public class Modifier {

	private AdjustmentCategory category;

	private int value;

	Set<ModifierListener> subscribers = new HashSet<>();

	public AdjustmentCategory getCategory() {
		return category;
	}

	public void setCategory(AdjustmentCategory category) {
		this.category = category;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
