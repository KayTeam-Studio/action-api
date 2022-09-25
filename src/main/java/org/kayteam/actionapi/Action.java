package org.kayteam.actionapi;

import org.bukkit.entity.Player;

public abstract class Action {

    private ActionManager actionManager;
    private final String format;

    protected Action(String format) {
        this.format = format;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public String getFormat() {
        return format;
    }

    public abstract void execute(Player player );

    private String getType() {

        String type = format;

        if ( format.contains(" ") ) {

            type = format.split( " " )[0];

        }

        type = type.replaceFirst( "\\[" , "");
        type = type.replaceFirst( "]" , "");

        return type;

    }

    public String getValue() {

        String value = "";

        if ( format.contains(" ") ) {

            value = format;

            value = value.substring( getType().length() + 3 );

        }

        return value;

    }

    @Override
    public String toString() {
        return getFormat();
    }
}