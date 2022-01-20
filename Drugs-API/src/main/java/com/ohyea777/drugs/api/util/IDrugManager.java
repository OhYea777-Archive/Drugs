package com.ohyea777.drugs.api.util;

import com.ohyea777.drugs.api.Drug;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface IDrugManager {

    void addDrug(Drug drug);

    void removeDrug(Drug drug);

    boolean isDrug(ItemStack item);

    boolean isDrug(Material material, short damage, ItemStack item);

    Drug getDrug(ItemStack item);

    Drug getDrug(Material material, short damage, ItemStack item);

}
