package me.loggits.mountain.bukkit.gui.actions;

import me.loggits.mountain.MountainHook;
import me.loggits.mountain.bukkit.gui.Action;
import me.loggits.mountain.bukkit.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class RunCommandAction implements Action {
    private final boolean closeInventory;
    private final List<String> commands;
    private GUI gui;

    public RunCommandAction(GUI gui, List<String> command) {
        this(gui, command, false);
    }

    public RunCommandAction(GUI gui, List<String> command, boolean closeInventory) {
        this.gui = gui;
        this.commands = command;
        this.closeInventory = closeInventory;
    }

    @Override
    public boolean attempt(Player player) {
        Bukkit.getScheduler().runTask(MountainHook.getInstance(), () -> {
            commands.forEach(command -> {
                Bukkit.dispatchCommand(player, command);
            });
        });
        return true;
    }

    public List<String> getCommand() {
        return commands;
    }
}
