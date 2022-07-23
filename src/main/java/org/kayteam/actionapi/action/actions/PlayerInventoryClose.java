package org.kayteam.actionapi.action.actions;

import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class PlayerInventoryClose extends Action {

    public PlayerInventoryClose() {
        super("[playerInventoryClose]", "");
    }

    @Override
    public void runAction(Player player) {
        player.closeInventory();
    }

}