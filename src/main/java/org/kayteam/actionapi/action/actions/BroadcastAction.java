package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class BroadcastAction extends Action {

    private String message = "";

    public BroadcastAction(String name, String message) {
        super(name);
        this.message = message;
    }

    public BroadcastAction(String message) {
        super("[broadcast]");
        this.message = message;
    }

    public BroadcastAction() {
        super("[broadcast]");
    }

    @Override
    public void runAction(Player player) {
        // PlaceholderAPI
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        // ChatColor
        message = ChatColor.translateAlternateColorCodes('&', message);
        // Send message
        Bukkit.getServer().broadcastMessage(message);
    }

}