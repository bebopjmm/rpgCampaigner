package com.rpgcampaigner.common;

import java.util.HashSet;
import java.util.Set;

/**
 * An DynamicValue manages a single score, such as an ability or hitPoints. It tracks all
 * modifiers, however temporary or permanent, against the base score. It notifies all subscribers
 * to changes in the value being managed.
 *
 * @author bebopjmm
 * @since 9/21/16
 */
public class DynamicValue implements ModifierListener {

	/**
	 * The baseValue is the raw score without any modifiers.
	 */
	private int baseValue;

	/**
	 * The currentValue is the calculated score with all currently applicable modifiers
	 */
	private int currentValue;

	/**
	 * Optional identifying name for the adjustable value (useful for debugging)
	 */
	private String name = "";

	private Set<Modifier> modifiers = new HashSet<>();

	/**
	 * Instantiates a new DynamicValue, setting both base and current values to the provided
	 * baseValue.
	 *
	 * @param baseValue the base value without any modifiers
	 */
	public DynamicValue(int baseValue)
	{
		this.baseValue = baseValue;
		this.currentValue = baseValue;
//		subscribers = new ArrayList<AdjustableValueListener>();
	}

	public int getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(int baseValue) {
		this.baseValue = baseValue;
		recalc();
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized void addModifier(Modifier modifier) {
		if (this.modifiers.contains(modifier)) {
			return;
		}
		this.modifiers.add(modifier);
		modifier.getSubscribers().add(this);
		adjust(modifier.getValue());
	}

	public synchronized void removeModifier(Modifier modifier) {
		if (!this.modifiers.contains(modifier)) {
			return;
		}
		modifier.getSubscribers().remove(this);
		this.modifiers.remove(modifier);
		adjust(-modifier.getValue());
	}

	@Override
	public void onModifierChange(int delta) {
		if (delta == 0) {
			return;
		}
		adjust(delta);
	}

	/**
	 * Recalculates the current value (baseValue + all modifiers)
	 */
	void recalc() {
		this.currentValue = baseValue;
		this.modifiers.stream().forEach(a -> this.currentValue += a.getValue());
	}

	void adjust(int delta) {
		this.currentValue += delta;
		// TODO notify listeners
	}
}
