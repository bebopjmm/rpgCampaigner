package com.rpgcampaigner.common;

/**
 * The AdjustmentListener interface enables an object to be notified in changes to an Adjustment
 * value.
 *
 * @author bebopjmm
 * @since 11/15/16
 *
 */
public interface AdjustmentListener
{

	/**
	 * This method will be invoked when an Adjustment changes value.
	 */
	void adjust(int delta);
}
