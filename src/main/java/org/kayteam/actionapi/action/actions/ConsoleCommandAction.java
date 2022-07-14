package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class ConsoleCommandAction extends Action {

    private String command = "";

    public ConsoleCommandAction(String name, String command) {
        super(name);
        this.command = command;
    }

    public ConsoleCommandAction(String command) {
        super("[console-command]");
        this.command = command;
    }

    public ConsoleCommandAction() {
        super("[console-command]");
    }

    @Override
    public void runAction(Player player) {
        // PlaceholderAPI
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            command = PlaceholderAPI.setPlaceholders(player, command);
        }
        // ChatColor
        command = ChatColor.translateAlternateColorCodes('&', command);
        // Run command
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }

}