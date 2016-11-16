package com.rpgcampaigner.common.d20;

import java.util.function.Supplier;

/**
 * @author jmccormick
 * @since 10/20/16
 */
public enum Ability {
	STR("Strength"),
	DEX("Dexterity"),
	CON("Constitution"),
	INT("Intelligence"),
	WIS("Wisdom"),
	CHA("Charisma");

	private final String longName;

	Ability(String longName) {
		this.longName = longName;
	}

	public String getLongName() {
		return longName;
	}

	/**
	 * Returns the ability modifier for the provided value
	 *
	 * @param value
	 * @return modifier
	 *
	 * TODO investigate turning this into a lambda function
	 */
//	Supplier<Integer> getValue = (i) -> i/2 -5;
	public static short getModifier(short value)
	{
		return (short)(value / 2 - 5);
	}
}
