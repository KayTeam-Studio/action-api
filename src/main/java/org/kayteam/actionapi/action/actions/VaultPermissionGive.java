package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class VaultPermissionGive extends Action {

    public VaultPermissionGive(String value) {
        super("[vaultPermissionGive]", value);
    }

    @Override
    public void runAction(Player player) {
        if (getActionManager() != null) {
            if (getActionManager().isPermissionEnabled()) {
                String permission = getValue();
                if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    permission = PlaceholderAPI.setPlaceholders(player, permission);
                }
                getActionManager().getPermission().playerAdd(player, permission);
            }
        }
    }

}