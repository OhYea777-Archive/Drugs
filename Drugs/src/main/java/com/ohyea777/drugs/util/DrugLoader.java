package com.ohyea777.drugs.util;

import java.util.ArrayList;

import com.ohyea777.drugs.api.util.IDrugManager;
import com.ohyea777.drugs.api.libs.com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.api.Drug;

public class DrugLoader implements IDrugManager {

	@SerializedName("Drugs") private ArrayList<Drug> drugs;

	private void checkDrugs() {
		if (drugs == null) drugs = new ArrayList<Drug>();
	}

	public void addDrug(Drug drug) {
		checkDrugs();

		drugs.add(drug);
	}

	public void removeDrug(Drug drug) {
		checkDrugs();

		drugs.remove(drug);
	}

	public boolean isDrug(ItemStack item) {
		return item != null && isDrug(item.getType(), item.getDurability(), item);
	}

	public boolean isDrug(Material material, short damage, ItemStack item) {
		for (Drug d : drugs) {
			if (d.getMaterial().name().equals(material.name()) && d.getDamage().equals(damage)) {
				if (item != null && d.usesLore()) if (item.hasItemMeta() && item.getItemMeta().hasLore()) for (String lore : item.getItemMeta().getLore()) if (d.getLore().equals(ChatColor.stripColor(lore))) return true;
				else return true;
			}
		}

		return false;
	}

	public Drug getDrug(ItemStack item) {
		return item != null ? getDrug(item.getType(), item.getDurability(), item) : null;
	}

	public Drug getDrug(Material material, short damage, ItemStack item) {
		if (isDrug(material, damage, item)) for (Drug d : drugs) if (d.getMaterial().equals(material) && d.getDamage().equals(damage)) return d;

		return null;
	}

}
