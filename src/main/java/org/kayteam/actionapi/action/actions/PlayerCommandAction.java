package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class PlayerCommandAction extends Action {

    private String command = "";

    public PlayerCommandAction(String name, String command) {
        super(name);
        this.command = command;
    }

    public PlayerCommandAction(String command) {
        super("[player-command]");
        this.command = command;
    }

    public PlayerCommandAction() {
        super("[player-command]");
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
        Bukkit.getServer().dispatchCommand(player, command);
    }

}