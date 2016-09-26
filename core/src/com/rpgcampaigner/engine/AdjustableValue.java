package com.rpgcampaigner.engine;

/**
 * AdjustableValue manages a single score, such as an ability or hitPoints. It tracks all
 * adjustments, however temporary or permanent, against the base score. It notifies all subscribers
 * to changes in the value being managed.
 *
 * @author bebopjmm
 * @since 9/21/16
 */
public class AdjustableValue {

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
}
