package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class MoneySetAction extends Action {

    private String amountString = "0";

    public MoneySetAction(String name, String amountString) {
        super(name);
        this.amountString = amountString;
    }

    public MoneySetAction(String amountString) {
        super("[money-set]");
        this.amountString = amountString;
    }

    public MoneySetAction() {
        super("[money-set]");
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    @Override
    public void runAction(Player player) {
        if (getActionManager() != null) {
            if (getActionManager().isEconomyEnabled()) {
                if (!amountString.equals("")) {
                    if (!amountString.contains(" ")) {
                        try {
                            if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                amountString = PlaceholderAPI.setPlaceholders(player, amountString);
                            }
                            double amount = Double.parseDouble(amountString);
                            Economy economy = getActionManager().getEconomy();
                            economy.withdrawPlayer(player, economy.getBalance(player));
                            economy.depositPlayer(player, amount);
                        } catch (NumberFormatException ignore) {}
                    }
                }
            }
        }
    }

}