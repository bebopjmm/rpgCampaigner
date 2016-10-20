package com.rpgcampaigner.common.d20;

import java.util.UUID;

/**
 * @author jmccormick
 * @since 10/20/16
 */
public abstract class D20Actor {

	private UUID uniqueId;

	private String name;

	private Alignment alignment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
}
