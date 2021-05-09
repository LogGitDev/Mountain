package me.loggits.mountain.bukkit.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GUIImpl implements GUI {
    HashMap<Integer, Button> buttons = new HashMap<>();

    public GUIImpl() {

    }

    @Override
    public void onClick(Player player, int slot, ItemStack item, InventoryClickEvent event) {
        Button button = buttons.get(slot);
        if (button != null) {
            button.click(player, slot, item, event);
        }
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
