package org.kayteam.actionapi.action;

import org.bukkit.entity.Player;

public abstract class Action {

    private final String name;
    private ActionManager actionManager;

    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public abstract void runAction(Player player);

}