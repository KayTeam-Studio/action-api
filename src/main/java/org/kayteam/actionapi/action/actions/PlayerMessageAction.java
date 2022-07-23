package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class PlayerMessageAction extends Action {

    public PlayerMessageAction(String value) {
        super("[playerMessage]", value);
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
        player.sendMessage(message);
    }

}