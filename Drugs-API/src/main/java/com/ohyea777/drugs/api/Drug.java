package com.ohyea777.drugs.api;

import java.util.ArrayList;
import java.util.List;

import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Drug {

	@SerializedName("Name") private String name;
	@SerializedName("Material") private String material;
	@SuppressWarnings("Damage") private Short damage;
	@SuppressWarnings("UsageMessage") private String usageMessage;
	@SuppressWarnings("DruggedMessage") private String druggedMessage;
	@SuppressWarnings("Permission") private String permission;
	@SuppressWarnings("Lore") private String lore;
	@SuppressWarnings("Effects") private List<Effect> effects;
	@SuppressWarnings("RandomEffects") private List<RandomEffect> randomEffects;
	@SuppressWarnings("RandomGroupEffects") private List<RandomGroupEffect> randomGroupEffects;
	@SuppressWarnings("Swirls") private List<Swirl> swirls;
	@SuppressWarnings("ParticleEffects") private List<ParticleEffect> particleEffects;
	@SuppressWarnings("Sounds") private List<CustomSound> sounds;
	@SuppressWarnings("Commands") private List<String> commands;
	@SuppressWarnings("Worlds") private List<String> worlds;

	public Drug() { }

	public Drug(Material material) {
		this.material = material.name();
	}

	private Plugin getInstance() {
		return Bukkit.getPluginManager().getPlugin("Drugs");
	}

	public String getName() {
		if (name == null) name = material + ":" + getDamage();

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Material getMaterial() {
		return org.bukkit.Material.matchMaterial(material);
	}

	public void setMaterial(Material material) {
		this.material = material.name();
	}

	public Short getDamage() {
		if (damage == null) damage = 0;

		return damage;
	}

	public void setDamage(short damage) {
		this.damage = damage;
	}

	public String getUsageMessage() {
		return usageMessage;
	}

	public void setUsageMessage(String usageMessage) {
		this.usageMessage = usageMessage;
	}

	public String getDruggedMessage() {
		return druggedMessage;
	}

	public void setDruggedMessage(String druggedMessage) {
		this.druggedMessage = druggedMessage;
	}

	public String getPermission() {
		if (permission == null) permission = "";

		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public boolean usesPermission() {
		return (getInstance().getConfig().isBoolean("Options.Use_Custom_Perms") && getInstance().getConfig().getBoolean("Options.Use_Custom_Perms")) || (getInstance().getConfig().isBoolean("Options.UseCustomPerms") && getInstance().getConfig().getBoolean("Options.UseCustomPerms"));
	}

	public boolean hasPermission(Player player) {
		if (player != null) {
			if (usesPermission()) return player.hasPermission("drugs.use." + (getPermission().isEmpty() ? getName() : getPermission())) || player.hasPermission("drugs.use.*");
			else return player.hasPermission("drugs.use");
		}

		return true;
	}
	
	public String getLore() {
		return lore;
	}
	
	public void setLore(String lore) {
		this.lore = lore;
	}
	
	public boolean usesLore() {
		return getLore() != null;
	}

	public List<Effect> getEffects() {
		if (effects == null) effects = new ArrayList<Effect>();

		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

	public List<RandomEffect> getRandomEffects() {
		if (randomEffects == null) randomEffects = new ArrayList<RandomEffect>();

		return randomEffects;
	}

	public void setRandomEffects(List<RandomEffect> randomEffects) {
		this.randomEffects = randomEffects;
	}

	public List<RandomGroupEffect> getRandomGroupEffects() {
		if (randomGroupEffects == null) randomGroupEffects = new ArrayList<RandomGroupEffect>();

		return randomGroupEffects;
	}

	public void setRandomGroupEffects(List<RandomGroupEffect> randomGroupEffects) {
		this.randomGroupEffects = randomGroupEffects;
	}

	public List<Swirl> getSwirls() {
		if (swirls == null) swirls = new ArrayList<Swirl>();

		return swirls;
	}

	public void setSwirls(List<Swirl> swirls) {
		this.swirls = swirls;
	}

	public List<ParticleEffect> getParticleEffects() {
		if (particleEffects == null) particleEffects = new ArrayList<ParticleEffect>();

		return particleEffects;
	}

	public void setParticleEffects(List<ParticleEffect> particleEffects) {
		this.particleEffects = particleEffects;
	}

	public List<CustomSound> getSounds() {
		if (sounds == null) sounds = new ArrayList<CustomSound>();

		return sounds;
	}

	public void setSounds(List<CustomSound> sounds) {
		this.sounds = sounds;
	}

	public List<String> getCommands() {
		if (commands == null) commands = new ArrayList<String>();

		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	public List<String> getWorlds() {
		if (worlds == null) worlds = new ArrayList<String>();

		return worlds;
	}

	public void setWorlds(List<String> worlds) {
		this.worlds = worlds;
	}

	public boolean enabledInWorld(World world) {
		for (String w : getWorlds()) if (w.equalsIgnoreCase(world.getName())) return true;

		return false;
	}

	public void doDrug(LivingEntity entity, boolean drugged, Player druggedBy) {
		if (!enabledInWorld(entity.getWorld())) return;

		Player player = null;

		if (entity instanceof Player) player = (Player) entity;

		if (hasPermission(player)) {
			for (Effect e : getEffects()) e.doEffect(entity);

			for (RandomEffect e : getRandomEffects()) e.doEffect(entity);

			for (RandomGroupEffect e : getRandomGroupEffects()) e.doEffects(entity);

			for (Swirl s : getSwirls()) s.doEffect(entity);

			for (ParticleEffect e : getParticleEffects()) e.doEffect(entity);

			for (CustomSound s : getSounds()) s.doSound(player);
		} else {
			player.sendMessage(Messages.NO_PERM.toString());

			return;
		}

		if (player != null) {
			for (String c : getCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), c.replace("{player}", player.getName()).replace("{drug}", getName()));

			if (drugged && druggedBy != null) {
				if (getDruggedMessage() == null) player.sendMessage(Messages.DRUGGED.toString().replace("{player}", druggedBy.getName()).replace("{drug}", getName()));
				else player.sendMessage(Messages.PREFIX.get(getDruggedMessage().replace("{player}", druggedBy.getName()).replace("{drug}", getName())));
			} else {
				if (getUsageMessage() == null) player.sendMessage(Messages.DRUG_USE.toString().replace("{drug}", getName()));
				else player.sendMessage(Messages.PREFIX.get(getUsageMessage().replace("{drug}", getName())));
			}
		}
	}

}
