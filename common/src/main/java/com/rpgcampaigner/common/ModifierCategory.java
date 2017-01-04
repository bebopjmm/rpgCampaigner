package com.rpgcampaigner.common;

/**
 * ModifierCategory is a classification for the source of a Modifier
 *
 * @author jmccormick
 * @since 9/26/16
 */
public enum ModifierCategory {
	// For Modifiers that are permanent
	INHERENT,

	// Modifiers that is granted due to something being equipped
	EQUIPMENT,

	// Modifiers that are temporary
	EFFECT
}
