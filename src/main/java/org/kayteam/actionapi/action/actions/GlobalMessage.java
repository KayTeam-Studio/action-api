package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class GlobalMessage extends Action {

    public GlobalMessage(String value) {
        super("[globalMessage]", value);
    }

    @Override
    public void runAction(Player player) {
        String message = getValue();
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