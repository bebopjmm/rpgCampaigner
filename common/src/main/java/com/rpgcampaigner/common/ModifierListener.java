package com.rpgcampaigner.common;

/**
 * The ModifierListener interface enables an object to be notified in changes to an Modifier
 * value.
 *
 * @author bebopjmm
 * @since 11/15/16
 *
 */
public interface ModifierListener
{

	/**
	 * This method will be invoked when an Modifier changes value.
	 */
	void onModifierChange (int delta);
}
