package org.kayteam.actionapi.action.actions;

import com.cryptomorin.xseries.XSound;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class PlayerSound extends Action {

    public PlayerSound(String value) {
        super("[playerSound]", value);
    }

    @Override
    public void runAction(Player player) {
        String value = getValue();
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            value = PlaceholderAPI.setPlaceholders(player, value);
        }
        XSound xSound;
        float volume = 1.0F;
        float pitch = 1.0F;
        if (value.contains(" ")) {
            String[] values = value.split(" ");
            xSound = XSound.matchXSound(values[0]).orElse(null);
            if (values.length > 1) {
                try {
                    volume = Float.parseFloat(values[1]);
                } catch (NumberFormatException ignore) {}
                if (values.length > 2) {
                    try {
                        pitch = Float.parseFloat(values[2]);
                    } catch (NumberFormatException ignore) {}
                }
            }
        } else {
            xSound = XSound.matchXSound(value).orElse(null);
        }
        if (xSound != null) {
            player.playSound(player.getLocation(), xSound.parseSound(), volume, pitch);
        }
    }

}