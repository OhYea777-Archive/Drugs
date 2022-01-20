package com.ohyea777.drugs.api;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

public class Effect {

	@SerializedName("EffectType") private String effectType;
	@SerializedName("Duration") private Integer duration;
	@SerializedName("Strength") private Integer strength;
	@SerializedName("Ambient") private Boolean ambient;
	
	public Effect() { }
	
	public Effect(String effectType) {
		this.effectType = effectType;
	}

	public String getStringEffectType() {
		return effectType;
	}
	
	public void setEffectType(String effectType) {
		this.effectType = effectType;
	}

	public Integer getDuration() {
		if (duration == null) duration = 10;
		
		return duration * 20;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Integer getStrength() {
		if (strength == null) strength = 0;
		
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public Boolean isAmbient() {
		if (ambient == null) ambient = false;
		
		return ambient;
	}
	
	public void setAmbient(boolean ambient) {
		this.ambient = ambient;
	}
	
	public void doEffect(LivingEntity entity) {
		if (entity == null || EffectTypes.toPotion(getStringEffectType()) == null) return;
		
		entity.addPotionEffect(new PotionEffect(EffectTypes.toPotion(getStringEffectType()), getDuration(), getStrength(), isAmbient()));
	}
	
}
