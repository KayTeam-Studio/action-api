package org.kayteam.actionapi.actions;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.kayteam.actionapi.Action;

public class RemovePotionAction extends Action {

    PotionEffectType potionEffectType;

    public RemovePotionAction(String format) {
        super(format);
        try{
            String value = getValue();

            potionEffectType = PotionEffectType.getByName(value);
        }catch (Exception e){
            getActionManager().getJavaPlugin().getLogger().info( "Invalid potion effect type format from '" + getFormat());
        }
    }

    @Override
    public void execute(Player player) {
        player.removePotionEffect(potionEffectType);
    }
}
