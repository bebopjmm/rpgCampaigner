package com.rpgcampaigner.pfrpg.entity.domain;

/**
 * Enumeration of Character's abilities, as per PFRPG ruleset.
 *
 * @author bebopjmm
 * @since 9/26/16
 */
public enum AbilitiesEnum {
	STR, DEX, CON, INT, WIS, CHA;

	/**
	 * Returns the ability modifier for the provided value
	 *
	 * @param value
	 * @return modifier
	 * @since sprint-0.1
	 */
	public static int getModifier(int value)
	{
		return (value / 2 - 5);
	}
}
