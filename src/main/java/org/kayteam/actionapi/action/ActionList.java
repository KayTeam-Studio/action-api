package org.kayteam.actionapi.action;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ActionList {

    private final String name;
    private final List<Action> actions;

    public ActionList(String name, List<Action> actions) {
        this.name = name;
        this.actions = actions;
    }

    public ActionList(String name) {
        this.name = name;
        actions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Action getAction(String name) {
        for (Action action:actions) {
            if (action.getName().equals(name)) {
                return action;
            }
        }
        return null;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(String name) {
        actions.removeIf(action -> action.getName().equals(name));
    }

    public void runActions(Player player) {
        for (Action action:actions) {
            action.runAction(player);
        }
    }

}