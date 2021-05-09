package me.loggits.mountain.bukkit.events;

import me.loggits.mountain.bukkit.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof GUI) {
            e.setCancelled(true);
            ((GUI) e.getInventory().getHolder()).onClick(
                    (Player) e.getWhoClicked(),
                    e.getRawSlot(),
                    e.getCurrentItem(),
                    e
            );
        }
    }

}
