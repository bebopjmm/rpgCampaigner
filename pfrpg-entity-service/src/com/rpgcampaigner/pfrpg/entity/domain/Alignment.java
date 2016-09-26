package com.rpgcampaigner.pfrpg.entity.domain;

/**
 * @author jmccormick
 * @since 9/26/16
 */
public enum Alignment {
	LG("Lawful Good"),
	NG("Neutral Good"),
	CG("Chaotic Good"),
	LN("Lawful Neutral"),
	TN("True Neutal"),
	CN("Chaotic Neutral"),
	LE("Lawful Evil"),
	NE("Neutral Evil"),
	CE("Chaotic Evil");

	private final String longName;

	Alignment(String longName)
	{
		this.longName = longName;
	}

	public String longName()
	{
		return longName;
	}
}
