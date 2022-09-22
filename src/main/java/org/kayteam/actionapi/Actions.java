package org.kayteam.actionapi;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Actions {

    private final LinkedHashMap< String , Action> actions = new LinkedHashMap<>();

    public LinkedHashMap< String , Action> getActions() {
        return actions;
    }

    public boolean existAction(String format ) {
        return actions.containsKey( format );
    }

    public void addAction(Action action) {
        actions.put( action.getFormat() , action);
    }

    public Action getAction(String format ) {
        return actions.get( format );
    }

    public void removeAction(String format ) {
        actions.remove( format );
    }

    public void executeAll( Player player ) {
        for ( Action action : actions.values() ) {
            action.execute( player );
        }
    }

    public List<String> serialize() {
        List<String> result = new ArrayList<>();

        for ( Action action : actions.values() ) {

            result.add( action.toString() );

        }

        return result;
    }

}