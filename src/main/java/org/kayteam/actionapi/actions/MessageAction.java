package org.kayteam.actionapi.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.Action;

public class MessageAction extends Action {

    public MessageAction(String format) {
        super(format);
    }

    @Override
    public void execute( Player player ) {

        String value = getValue();

        if ( getActionManager().getJavaPlugin().getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {
            value = PlaceholderAPI.setPlaceholders( player , value );
        }

        value = ChatColor.translateAlternateColorCodes( '&' , value );

        player.sendMessage( value );

    }

}