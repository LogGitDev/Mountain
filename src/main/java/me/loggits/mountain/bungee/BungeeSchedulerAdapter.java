package me.loggits.mountain.bungee;

import me.loggits.mountain.common.adapters.SchedulerAdapter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;

public class BungeeSchedulerAdapter implements SchedulerAdapter {

    private final Plugin plugin;

    public BungeeSchedulerAdapter(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void runTask(Runnable runnable) {
        runTaskAsynchronously(runnable);
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        getScheduler().runAsync(this.plugin, runnable);
    }

    public TaskScheduler getScheduler() {
        return this.plugin.getProxy().getScheduler();
    }
}

