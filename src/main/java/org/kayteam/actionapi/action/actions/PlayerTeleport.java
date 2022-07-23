package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class PlayerTeleport extends Action {

    public PlayerTeleport(String value) {
        super("[playerTeleport]", value);
    }

    @Override
    public void runAction(Player player) {
        if (!getValue().equals("")) {
            if (getValue().contains(" ")) {
                String coordinatesFormat = getValue();
                if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    coordinatesFormat = PlaceholderAPI.setPlaceholders(player, coordinatesFormat);
                }
                String[] values = coordinatesFormat.split(" ");
                String world = values[0];
                if (Bukkit.getServer().getWorld(world) != null) {
                    if (values.length > 1) {
                        try {
                            double x = Double.parseDouble(values[1]);
                            if (values.length > 2) {
                                try {
                                    double y = Double.parseDouble(values[2]);
                                    if (values.length > 3) {
                                        try {
                                            double z = Double.parseDouble(values[3]);
                                            if (values.length > 4) {
                                                try {
                                                    float yaw = Float.parseFloat(values[4]);
                                                    if (values.length > 5) {
                                                        try {
                                                            float pitch = Float.parseFloat(values[5]);
                                                            player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
                                                        } catch (NumberFormatException ignore) {
                                                            player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                                                        }
                                                    } else {
                                                        player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                                                    }
                                                } catch (NumberFormatException ignore) {
                                                    player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                                                }
                                            } else {
                                                player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                                            }
                                        } catch (NumberFormatException ignore) {}
                                    }
                                } catch (NumberFormatException ignore) {}
                            }
                        } catch (NumberFormatException ignore) {}
                    }
                }
            }
        }
    }

}