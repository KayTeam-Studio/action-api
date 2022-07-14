package org.kayteam.actionapi.action.actions;

import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

public class InventoryCloseAction extends Action {

    public InventoryCloseAction(String name) {
        super(name);
    }

    public InventoryCloseAction() {
        super("[inventory-close]");
    }

    @Override
    public void runAction(Player player) {
        player.closeInventory();
    }

}