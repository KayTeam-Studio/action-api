package org.kayteam.actionapi.action;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;

public class ActionManager {

    private Economy economy;
    private boolean economyEnabled;
    private final HashMap<String, ActionList> actionLists;
    private final HashMap<String, Action> actions;

    public ActionManager() {
        actionLists = new HashMap<>();
        actions = new HashMap<>();
    }

    public void register() {
        economyEnabled = setupEconomy();
    }

    public HashMap<String, ActionList> getActionLists() {
        return actionLists;
    }

    public ActionList getActionList(String name) {
        return actionLists.get(name);
    }

    public  void addActionList(ActionList actionList) {
        actionLists.put(actionList.getName(), actionList);
    }

    private void removeActionList(String name) {
        actionLists.remove(name);
    }

    public HashMap<String, Action> getActions() {
        return actions;
    }

    public Action getAction(String name) {
        return actions.get(name);
    }

    public void addAction(Action action) {
        actions.put(action.getName(), action);
    }

    public void removeAction(String name) {
        actions.remove(name);
    }

    public void runAction(Player player, Action action) {
        action.runAction(player);
    }

    private boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public Economy getEconomy() {
        return economy;
    }

    public boolean isEconomyEnabled() {
        return economyEnabled;
    }

}