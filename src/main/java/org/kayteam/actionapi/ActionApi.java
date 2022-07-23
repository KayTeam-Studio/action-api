package org.kayteam.actionapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.actionapi.action.ActionManager;
import org.kayteam.actionapi.utils.BrandSender;

public final class ActionApi extends JavaPlugin {

    private final ActionManager actionManager = new ActionManager();
    private static ActionApi actionApi;

    @Override
    public void onEnable() {
        actionApi = this;
        actionManager.register(this);
        BrandSender.sendVersionStatus(this, getServer().getConsoleSender(), "&aEnabled");
    }

    @Override
    public void onDisable() {
        BrandSender.sendVersionStatus(this, getServer().getConsoleSender(), "&cDisabled");
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public static ActionApi getInstance() {
        return actionApi;
    }

}