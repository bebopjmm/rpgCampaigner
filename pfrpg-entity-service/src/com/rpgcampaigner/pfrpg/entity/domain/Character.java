package com.rpgcampaigner.pfrpg.entity.domain;

import java.util.EnumMap;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bebopjmm
 * @since 9/26/16
 */
public class Character {

	private UUID uniqueId;

	private String name;

	private Optional<String> playerName;

	private Alignment alignment;

	private String gender;

	private EnumMap<AbilitiesEnum, String> abilities;

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
}
