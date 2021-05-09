package me.loggits.mountain.bukkit.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface GUI extends InventoryHolder {

    void onClick(Player player, int slot, ItemStack item, InventoryClickEvent event);
}
