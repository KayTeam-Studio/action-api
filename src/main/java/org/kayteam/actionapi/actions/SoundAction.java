package org.kayteam.actionapi.actions;

import com.cryptomorin.xseries.XSound;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.Action;

public class SoundAction extends Action {

    public SoundAction(String format) {
        super(format);
    }

    @Override
    public void execute(Player player) {

        try {
            String value = getValue();

            if ( getActionManager().getJavaPlugin().getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {
                value = PlaceholderAPI.setPlaceholders( player , value );
            }

            Sound sound;
            float volume = 1;
            float pitch = 1;

            if ( value.contains(" ") ) {

                String[] values = value.split( " " );

                sound = Sound.valueOf( values[0] );

                if ( values.length >= 2 ) volume = Float.parseFloat( values[1] );

                if ( values.length >= 3 )  pitch = Float.parseFloat( values[2] );

            } else {
                sound = Sound.valueOf( value );
            }

            player.playSound( player.getLocation() , XSound.matchXSound(sound).parseSound() , volume , pitch);

        } catch (Exception e) {
            getActionManager().getJavaPlugin().getLogger().info( "Invalid sound format from '" + getFormat());
        }

    }

}