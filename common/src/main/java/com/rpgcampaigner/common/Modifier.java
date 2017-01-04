package com.rpgcampaigner.common;

import java.util.HashSet;
import java.util.Set;

/**
 * An Modifier captures a singular alteration to a modifiable score (DynamicValue), such as an
 * AbilityEnum, Skill, or Save. Any change in the value of the Modifier triggers a notification event
 * to all subscribed listeners.
 *
 * @author bebopjmm
 * @since 11/15/16
 */
public class Modifier {

	private ModifierCategory category;

	private int value;

	/**
	 * convience identifying name for tracing and lookup
	 */
	private String name;

	Set<ModifierListener> subscribers = new HashSet<>();

	public ModifierCategory getCategory() {
		return category;
	}

	public void setCategory(ModifierCategory category) {
		this.category = category;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		int oldValue = this.value;
		this.value = value;
		notifyOfChange(this.value - oldValue);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ModifierListener> getSubscribers() {
		return subscribers;
	}

	void notifyOfChange(int delta) {
		this.subscribers.stream().forEach(s -> s.onModifierChange(delta));
	}
}
