package com.rpgcampaigner.common.d20;

/**
 * @author jmccormick
 * @since 10/20/16
 */
public enum AbilityEnum {
	STR("Strength"),
	DEX("Dexterity"),
	CON("Constitution"),
	INT("Intelligence"),
	WIS("Wisdom"),
	CHA("Charisma");

	private final String longName;

	AbilityEnum(String longName) {
		this.longName = longName;
	}

	public String getLongName() {
		return longName;
	}

	/**
	 * Returns the ability abilityModifier for the provided value
	 *
	 * @param value
	 * @return abilityModifier
	 * <p>
	 * TODO investigate turning this into a lambda function
	 */
	public static int getModifier(int value) {
		return value / 2 - 5;
	}
}
