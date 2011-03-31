package com.gmail.nossr50.woolplus;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Wool Plus for Bukkit
 *
 * @author nossr50, jascotty2
 */
public class WoolPlus extends JavaPlugin {

    private final WPPlayerListener playerListener = new WPPlayerListener(this);

    public void onEnable() {
        getServer().getPluginManager().registerEvent(
                Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
        System.out.println("WoolPlus version " + getDescription().getVersion() + " is enabled!");
    }

    public void onDisable() {
        //System.out.println("Wool Plus disabled!");
    }
}
