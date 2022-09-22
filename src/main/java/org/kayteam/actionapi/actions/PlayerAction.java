package org.kayteam.actionapi.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.Action;

public class PlayerAction extends Action {

    public PlayerAction(String format) {
        super(format);
    }

    @Override
    public void execute(Player player) {

        String value = getValue();

        if ( getActionManager().getJavaPlugin().getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {
            value = PlaceholderAPI.setPlaceholders( player , value );
        }

        Server server = getActionManager().getJavaPlugin().getServer();

        server.dispatchCommand( player , value );

    }

}