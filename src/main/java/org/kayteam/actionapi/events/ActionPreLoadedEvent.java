package org.kayteam.actionapi.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.kayteam.actionapi.action.Action;

public class ActionPreLoadedEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();
    private final String type;
    private final String value;
    private Action action;

    public ActionPreLoadedEvent(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}