package com.ohyea777.drugs.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ParticleEffect {

	@SerializedName("ParticleEffect") private String particleEffect;
	@SerializedName("Duration") private Integer duration;
	@SerializedName("Type") private Integer type;
	@SerializedName("PlayerOnly") private Boolean playerOnly;
	
	private transient Map<UUID, Integer> scheduleIDs = new HashMap<UUID, Integer>();
	
	public ParticleEffect() { }
	
	public ParticleEffect(String particleEffect) {
		this.particleEffect = particleEffect;
	}

	public String getParticleEffect() {
		return particleEffect;
	}

	public void setParticleEffect(String particleEffect) {
		this.particleEffect = particleEffect;
	}

	public Integer getDuration() {
		if (duration == null) duration = 10;
		
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Integer getType() {
		if (type == null) type = 0;
		
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean getPlayerOnly() {
		if (playerOnly == null) playerOnly = false;
		
		return playerOnly;
	}

	public void setPlayerOnly(Boolean playerOnly) {
		this.playerOnly = playerOnly;
	}

	public void doEffect(final LivingEntity entity) {
		if (entity == null || EffectTypes.toEffect(getParticleEffect()) == null) return;
		
		int scheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Drugs"), new Runnable() {
			
			private int count = 1;
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if (getPlayerOnly() && entity instanceof Player) ((Player) entity).playEffect(entity.getLocation(), EffectTypes.toEffect(getParticleEffect()), getType().intValue());
				else entity.getWorld().playEffect(entity.getLocation(), EffectTypes.toEffect(getParticleEffect()), getType().intValue());
				
				if (count >= getDuration() * 4 ) {
					Bukkit.getScheduler().cancelTask(scheduleIDs.get(entity.getUniqueId()));

					return;
				}
				
				count ++;
			}
		}, 5, 5);
		
		scheduleIDs.put(entity.getUniqueId(), scheduleID);
	}
	
}
