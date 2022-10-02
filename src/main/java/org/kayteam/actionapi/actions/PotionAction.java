package org.kayteam.actionapi.actions;

import com.cryptomorin.xseries.XPotion;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kayteam.actionapi.Action;

public class PotionAction extends Action {

    PotionEffect potionEffect;

    public PotionAction(String format) {
        super(format);
        try{
            String value = getValue();
            String[] values = value.split(" ");

            PotionEffectType potionEffectType = XPotion.matchXPotion(values[0]).get().getPotionEffectType();
            int duration = Integer.parseInt(values[1]);
            int amplifier = Integer.parseInt(values[2]);

            potionEffect = new PotionEffect(potionEffectType, duration, amplifier);
        }catch (Exception e){
            getActionManager().getJavaPlugin().getLogger().info( "Invalid potion effect format from '" + getFormat());
        }
    }

    @Override
    public void execute(Player player) {
        player.addPotionEffect(potionEffect);
    }
}
