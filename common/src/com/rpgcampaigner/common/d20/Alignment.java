package com.rpgcampaigner.common.d20;

/**
 * @author jmccormick
 * @since 10/20/16
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
