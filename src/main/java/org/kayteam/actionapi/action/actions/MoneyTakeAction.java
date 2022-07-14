package org.kayteam.actionapi.action.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class MoneyTakeAction extends Action {

    private String amountString = "0";

    public MoneyTakeAction(String name, String amountString) {
        super(name);
        this.amountString = amountString;
    }

    public MoneyTakeAction(String amountString) {
        super("[money-take]");
        this.amountString = amountString;
    }

    public MoneyTakeAction() {
        super("[money-take]");
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
                            getActionManager().getEconomy().withdrawPlayer(player, amount);
                        } catch (NumberFormatException ignore) {}
                    }
                }
            }
        }
    }

}