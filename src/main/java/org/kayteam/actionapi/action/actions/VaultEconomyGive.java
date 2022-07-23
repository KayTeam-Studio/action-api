package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class VaultEconomyGive extends Action {

    public VaultEconomyGive(String value) {
        super("[vaultEconomyGive]", value);
    }

    @Override
    public void runAction(Player player) {
        if (getActionManager() != null) {
            if (getActionManager().isEconomyEnabled()) {
                String amountString = getValue();
                if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    amountString = PlaceholderAPI.setPlaceholders(player, amountString);
                }
                try {
                    double amount = Double.parseDouble(amountString);
                    getActionManager().getEconomy().depositPlayer(player, amount);
                } catch (NumberFormatException ignore) {}
            }
        }
    }

}