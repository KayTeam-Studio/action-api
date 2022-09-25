package org.kayteam.actionapi.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.Action;

public class ActionBarAction extends Action {

    public ActionBarAction(String format) {
        super(format);
    }

    @Override
    public void execute(Player player) {
        String value = getValue();

        if ( getActionManager().getJavaPlugin().getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {
            value = PlaceholderAPI.setPlaceholders( player , value );
        }

        value = ChatColor.translateAlternateColorCodes( '&' , value );

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(value));
    }
}
