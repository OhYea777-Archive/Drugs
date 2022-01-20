package com.ohyea777.drugs.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.entity.LivingEntity;

public class RandomGroupEffect {

	@SerializedName("Effects") private List<Effect> effects;
	@SerializedName("Chance") private Integer chance;

	public RandomGroupEffect() { }
	
	public RandomGroupEffect(int chance) {
		this.chance = chance;
	}

	public RandomGroupEffect(List<Effect> effects) {
		this.effects = effects;
	}

	public RandomGroupEffect(List<Effect> effects, int chance) {
		this(effects);

		this.chance = chance;
	}

	public List<Effect> getEffects() {
		if (effects == null) effects = new ArrayList<Effect>();

		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

	public void addEffect(Effect effect) {
		getEffects().add(effect);
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

	public void doEffects(LivingEntity entity) {
		if (getRandPercent(getChance())) for (Effect e : getEffects()) e.doEffect(entity);
	}

}
