package me.loggits.mountain.bukkit;

import me.loggits.mountain.common.adapters.SchedulerAdapter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class BukkitSchedulerAdapter implements SchedulerAdapter {

    private final JavaPlugin plugin;

    public BukkitSchedulerAdapter(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void runTask(Runnable runnable) {
        getScheduler().runTask(this.plugin, runnable);
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        getScheduler().runTaskAsynchronously(this.plugin, runnable);
    }

    public BukkitScheduler getScheduler() {
        return this.plugin.getServer().getScheduler();
    }
}
