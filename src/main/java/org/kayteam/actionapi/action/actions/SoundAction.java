package org.kayteam.actionapi.action.actions;

import com.cryptomorin.xseries.XSound;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class SoundAction extends Action {

    private String soundFormat = "";

    public SoundAction(String name, String soundFormat) {
        super(name);
        this.soundFormat = soundFormat;
    }

    public SoundAction(String soundFormat) {
        super("[sound]");
        this.soundFormat = soundFormat;
    }

    public SoundAction() {
        super("[sound]");
    }

    public String getSoundFormat() {
        return soundFormat;
    }

    public void setSoundFormat(String soundFormat) {
        this.soundFormat = soundFormat;
    }

    @Override
    public void runAction(Player player) {
        if (!soundFormat.equals("")) {
            if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                soundFormat = PlaceholderAPI.setPlaceholders(player, soundFormat);
            }
            XSound xSound;
            String sound;
            float volume = 1.0F;
            float pitch = 1.0F;
            if (soundFormat.contains(" ")) {
                String[] values = soundFormat.split(" ");
                sound = values[0];
                xSound = XSound.matchXSound(sound).orElse(null);
                if (values.length > 1) {
                    try {
                        volume = Float.parseFloat(values[1]);
                    } catch (NumberFormatException ignore) {}
                    if (values.length > 2) {
                        try {
                            pitch = Float.parseFloat(values[2]);
                        } catch (NumberFormatException ignore) {}
                    }
                }
            } else {
                sound = soundFormat;
                xSound = XSound.matchXSound(sound).orElse(null);
            }
            if (xSound != null) {
                player.playSound(player.getLocation(), xSound.parseSound(), volume, pitch);
            }
        }
    }

}