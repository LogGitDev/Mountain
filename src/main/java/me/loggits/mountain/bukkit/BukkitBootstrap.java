package me.loggits.mountain.bukkit;

import me.loggits.mountain.common.MountainBootstrapper;
import me.loggits.mountain.common.adapters.ConfigAdapter;
import me.loggits.mountain.common.adapters.SchedulerAdapter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class BukkitBootstrap extends JavaPlugin implements MountainBootstrapper {
    private BukkitSchedulerAdapter scheduler;

    @Override
    public void onEnable() {
        this.scheduler = new BukkitSchedulerAdapter(this);
    }

    @Override
    public Logger getPluginLogger() {
        return getLogger();
    }

    @Override
    public SchedulerAdapter getScheduler() {
        return null;
    }

    @Override
    public Class<? extends ConfigAdapter> getConfigSystem() {
        return BukkitConfigAdapter.class;
    }

    @Override
    public String getVersion() {
        return getDescription().getVersion();
    }

    @Override
    public void disable() {
        throw new UnsupportedOperationException("Incomplete feature");
    }
}
