package org.kayteam.actionapi.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.Action;

public class ConsoleAction extends Action {

    public ConsoleAction(String format) {
        super(format);
    }

    @Override
    public void execute(Player player) {

        String value = getValue();

        if ( getActionManager().getJavaPlugin().getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {
            value = PlaceholderAPI.setPlaceholders( player , value );
        }

        Server server = getActionManager().getJavaPlugin().getServer();

        ConsoleCommandSender consoleCommandSender = server.getConsoleSender();

        server.dispatchCommand( consoleCommandSender , value );

    }

}