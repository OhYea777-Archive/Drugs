package com.ohyea777.drugs.api.util;

import com.ohyea777.drugs.api.Drug;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DrugUtils {

    private static IDrugManager instance;

    public static boolean isLoaded() {
        return instance != null;
    }

    public static void addDrug(Drug drug) {
        instance.addDrug(drug);
    }

    public static void removeDrug(Drug drug) {
        instance.removeDrug(drug);
    }

    public static boolean isDrug(ItemStack itemStack) {
        return instance.isDrug(itemStack);
    }

    public static boolean isDrug(Material material, short damage, ItemStack item) {
        return instance.isDrug(material, damage, item);
    }

    public static Drug getDrug(ItemStack item) {
        return instance.getDrug(item);
    }

    public static Drug getDrug(Material material, short damage, ItemStack item) {
        return instance.getDrug(material, damage, item);
    }

    public static void setDrugManager(IDrugManager instance) {
        DrugUtils.instance = instance;
    }

    public static IDrugManager getDrugManager() {
        return instance;
    }

}
