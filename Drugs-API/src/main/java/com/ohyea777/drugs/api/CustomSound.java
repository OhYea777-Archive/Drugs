package com.ohyea777.drugs.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CustomSound {

	@SerializedName("Sound") private String sound;
	@SerializedName("Volume") private Float volume;
	@SerializedName("Pitch") private Float pitch;
	@SerializedName("Duration") private Integer duration;
	@SerializedName("Repetitions") private Integer repetitions;
	
	private transient Map<UUID, Integer> scheduleIDs = new HashMap<UUID, Integer>();
	
	public CustomSound() { }
	
	public CustomSound(String sound) {
		this.sound = sound;
	}
	
	public String getSound() {
		return sound;
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	public Float getVolume() {
		if (volume == null) volume = 1F;
		
		return volume;
	}
	
	public void setVolume(float volume) {
		this.volume = volume;
	}
	
	public Float getPitch() {
		if (pitch == null) pitch = 1F;
		
		return pitch;
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public Integer getDuration() {
		if (duration == null) duration = 1;
		
		return duration * 20;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Integer getRepetitions() {
		if (repetitions == null) repetitions = 1;
		
		return repetitions;
	}
	
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	
	public void doSound(final Player player) {
		if (player == null) return;
		
		int scheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Drugs"), new Runnable() {
			
			int repetitions = 1;

			@SuppressWarnings("deprecation") @Override
			public void run() {
				player.playSound(player.getLocation(), getSound(), getPitch(), getVolume());
				
				if (repetitions >= getRepetitions()) {
					Bukkit.getScheduler().cancelTask(scheduleIDs.get(player.getUniqueId()));

					return;
				}
				
				repetitions ++;
			}
		}, getDuration(), getDuration());
		
		scheduleIDs.put(player.getUniqueId(), scheduleID);
	}
	
}
