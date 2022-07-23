package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class VaultEconomyTake extends Action {

    public VaultEconomyTake(String value) {
        super("[vaultEconomyTake]", value);
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
                    getActionManager().getEconomy().withdrawPlayer(player, amount);
                } catch (NumberFormatException ignore) {}
            }
        }
    }

}