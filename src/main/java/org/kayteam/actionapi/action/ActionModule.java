package org.kayteam.actionapi.action;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionModule {

    private final List<Action> actions;

    public ActionModule() {
        actions = new ArrayList<>();
    }

    public List<Action> getActions() {
        return actions;
    }

    public void runActions(Player player) {
        for (Action action:actions) {
            action.runAction(player);
        }
    }

}