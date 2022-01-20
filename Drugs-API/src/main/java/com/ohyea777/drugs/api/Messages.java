package com.ohyea777.drugs.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public enum Messages {

    PREFIX("Options.Prefix", "&0(&aDrugs&0)&f "),
    RELOAD("Messages.Reload", "Config Reloaded!"),
    NO_PERM("Messages.No_Perm", "&4You Do Not Have Permission!"),
    DRUG_USE("Messages.Drug_Use", "You Have Just Used {drug}"),
    DRUGGED("Messages.Drugged", "You have just been drugged with {drug} by {player}");

    private String loc, def;

    private Messages(String loc, String def) {
        this.loc = loc;
        this.def = def;
    }

    private Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("Drugs");
    }

    private String getString() {
        return (ordinal() != 0 ? PREFIX.getString() : "") + _(getPlugin().getConfig().isString(loc) ? getPlugin().getConfig().getString(loc) : def);
    }

    private String _(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public String toString() {
        return getString();
    }

    public String get(String s) {
        return getString() + _(s);
    }

}
