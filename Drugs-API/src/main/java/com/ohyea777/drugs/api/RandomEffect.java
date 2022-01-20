package com.ohyea777.drugs.api;

import java.util.Random;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.entity.LivingEntity;

public class RandomEffect extends Effect {

	@SerializedName("Chance") private Integer chance;
	
	public RandomEffect() { }
	
	public RandomEffect(String effectType) {
		super(effectType);
	}
	
	public RandomEffect(String effectType, int chance) {
		this(effectType);
		
		this.chance = chance;
	}
	
	public Integer getChance() {
		if (chance == null) chance = 50;
		
		return chance;
	}
	
	public void setChance(int chance) {
		this.chance = chance;
	}
	
	private boolean getRandPercent(int percent) {
	    Random rand = new Random();

	    return rand.nextInt(100) <= percent;
	}
	
	@Override
	public void doEffect(LivingEntity entity) {
		if (getRandPercent(getChance())) super.doEffect(entity);
	}
	
}
