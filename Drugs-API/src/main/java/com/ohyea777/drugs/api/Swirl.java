package com.ohyea777.drugs.api;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;

public class Swirl extends ParticleEffect {

	@SerializedName("Color") public String color;
	
	public Swirl() {
		super("potion_break");
	}
	
	public Swirl(String color) {
		this();
		
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String getParticleEffect() {
		return "potion_break";
	}
	
	@Override
	public Integer getType() {
		return EffectTypes.toColor(getColor()) == 0 ? 16384 : EffectTypes.toColor(getColor());
	}
	
}
