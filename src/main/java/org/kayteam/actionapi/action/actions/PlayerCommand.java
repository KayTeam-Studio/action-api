package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class PlayerCommand extends Action {

    public PlayerCommand(String value) {
        super("[playerCommand]" ,value);
    }

    @Override
    public void runAction(Player player) {
        String command = getValue();
        // PlaceholderAPI
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            command = PlaceholderAPI.setPlaceholders(player, command);
        }
        // ChatColor
        command = ChatColor.translateAlternateColorCodes('&', command);
        // Run command
        Bukkit.getServer().dispatchCommand(player, command);
    }

}