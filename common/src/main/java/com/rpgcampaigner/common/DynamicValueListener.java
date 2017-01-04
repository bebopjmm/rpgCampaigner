package com.rpgcampaigner.common;

/**
 * @author jmccormick
 * @since 12/9/16
 */
public interface DynamicValueListener {

	/**
	 * This method will be invoked when a DynamicValue changes value.
	 */
	void onValueChange (int updatedValue);
}
