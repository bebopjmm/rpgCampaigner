package com.rpgcampaigner.common.d20;

import com.rpgcampaigner.common.DynamicValue;
import com.rpgcampaigner.common.Modifier;
import com.rpgcampaigner.common.ModifierCategory;

/**
 * The AbilityValue extends the basic DynamicValue in order to provide event-driven notification
 * to subscribers of changes in the Ability modifier.
 * 
 * @author bebopjmm
 * @since 1/4/17
 */
public class AbilityValue extends DynamicValue {

	public static final int DEFAULT_VALUE = 10;

	AbilityEnum ability;

	Modifier abilityModifier = new Modifier();

	/**
	 * Initializes an AbilityValue to have the specified baseValue and no subscribers.
	 *
	 * @param baseValue
	 * 		base value for the AdjustableValue
	 * @param ability
	 * 		Ability that this AbilityValue represents
	 */
	public AbilityValue(int baseValue, AbilityEnum ability) {
		super(baseValue);
		this.ability = ability;
		this.setName(ability.name());
		this.abilityModifier.setCategory(ModifierCategory.INHERENT);
		this.abilityModifier.setName("ability." + ability.name());
		updateAbilityModifier();
	}

	public AbilityEnum getAbility() {
		return ability;
	}

	public Modifier getAbilityModifier() {
		return abilityModifier;
	}

	@Override
	public void setBaseValue(int baseValue) {
		super.setBaseValue(baseValue);
		updateAbilityModifier();
	}

	@Override
	public synchronized void addModifier(Modifier modifier) {
		super.addModifier(modifier);
		updateAbilityModifier();
	}

	@Override
	public synchronized void removeModifier(Modifier modifier) {
		super.removeModifier(modifier);
		updateAbilityModifier();
	}

	@Override
	public void onModifierChange(int delta) {
		super.onModifierChange(delta);
		updateAbilityModifier();
	}

	/**
	 * Updates the ability abilityModifier value, which notifies all the abilityModifier subscribers.
	 */
	void updateAbilityModifier() {
		abilityModifier.setValue(AbilityEnum.getModifier(this.getCurrentValue()));
	}
}
