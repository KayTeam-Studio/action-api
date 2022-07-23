package org.kayteam.actionapi.action;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.actionapi.action.actions.*;
import org.kayteam.actionapi.events.ActionPreLoadedEvent;

import java.util.List;

public class ActionManager {

    private JavaPlugin javaPlugin;
    private Economy economy;
    private Permission permission;

    public void register(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        // economy and permission
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            // economy
            RegisteredServiceProvider<Economy> economyRSP = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            if (economyRSP != null) economy = economyRSP.getProvider();
            // permission
            RegisteredServiceProvider<Permission> permissionRSP = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
            if (permissionRSP != null) permission = permissionRSP.getProvider();
        }
    }

    public Economy getEconomy() {
        return economy;
    }

    public boolean isEconomyEnabled() {
        return economy != null;
    }

    public Permission getPermission() {
        return permission;
    }

    public boolean isPermissionEnabled() {
        return permission != null;
    }

    public ActionModule loadActionList(List<String> actionFormats) {
        ActionModule actionModule = new ActionModule();
        for (String actionFormat:actionFormats) {
            actionModule.getActions().add(loadAction(actionFormat));
        }
        return actionModule;
    }

    public Action loadAction(String actionFormat) {
        String type = getType(actionFormat);
        String value = getValue(actionFormat);
        Action action = new Action(type, value) { @Override public void runAction(Player player) {} };

        ActionPreLoadedEvent actionPreLoadedEvent = new ActionPreLoadedEvent(type, value);
        if (javaPlugin != null) {
            javaPlugin.getServer().getPluginManager().callEvent(actionPreLoadedEvent);
        }
        if (actionPreLoadedEvent.getAction() != null) {
            action = actionPreLoadedEvent.getAction();
        } else {
            switch (type) {
                case "[player]": {
                    action = new PlayerCommand(value);
                    break;
                }
                case "[console]": {
                    action = new ConsoleCommand(value);
                    break;
                }
                case "[message]": {
                    action = new PlayerMessageAction(value);
                    break;
                }
                case "[broadcast]": {
                    action = new GlobalMessage(value);
                    break;
                }
                case "[close]": {
                    action = new PlayerInventoryClose();
                    break;
                }
                case "[sound]": {
                    action = new PlayerSound(value);
                    break;
                }
                case "[takemoney]": {
                    action = new VaultEconomyGive(value);
                    break;
                }
                case "[givemoney]": {
                    action = new VaultEconomyTake(value);
                    break;
                }
                case "[setmoney]": {
                    action = new VaultEconomySet(value);
                    break;
                }
                case "[givepermission]": {
                    action = new VaultPermissionGive(value);
                    break;
                }
                case "[takepermission]": {
                    action = new VaultPermissionTake(value);
                    break;
                }
                case "[teleport]": {
                    action = new PlayerTeleport(value);
                    break;
                }
            }
        }
        action.setActionManager(this);
        return action;
    }

    private String getType(String action) {
        return action.split(" ")[0];
    }

    private String getValue(String action) {
        if (action.contains(" ")) {
            return action.replace(getType(action) + " ", "");
        } else {
            return "";
        }
    }

}