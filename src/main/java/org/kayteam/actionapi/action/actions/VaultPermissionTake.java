package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class VaultPermissionTake extends Action {

    public VaultPermissionTake(String value) {
        super("[vaultPermissionTake]", value);
    }

    @Override
    public void runAction(Player player) {
        if (getActionManager() != null) {
            if (getActionManager().isPermissionEnabled()) {
                String permission = getValue();
                if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    permission = PlaceholderAPI.setPlaceholders(player, permission);
                }
                getActionManager().getPermission().playerRemove(player, permission);
            }
        }
    }

}