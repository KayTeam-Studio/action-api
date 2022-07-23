package org.kayteam.actionapi.action;

import org.bukkit.entity.Player;

public class Action {

    private final String type;
    private final String value;
    private ActionManager actionManager;

    public Action(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public void runAction(Player player) {}

}