package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class MessageAction extends Action {

    private String message = "";

    public MessageAction(String name, String message) {
        super(name);
        this.message = message;
    }

    public MessageAction(String message) {
        super("[message]");
        this.message = message;
    }

    public MessageAction() {
        super("[message]");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        player.sendMessage(message);
    }

}