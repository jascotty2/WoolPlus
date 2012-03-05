package com.gmail.nossr50.woolplus;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Wool Plus for Bukkit
 *
 * @author nossr50, jascotty2
 */
public class WoolPlus extends JavaPlugin {

    private final WPPlayerListener playerListener = new WPPlayerListener(this);

	@Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(playerListener, this);
    }

	@Override
    public void onDisable() {
        //System.out.println("Wool Plus disabled!");
    }
}
