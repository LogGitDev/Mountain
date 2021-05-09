package me.loggits.mountain.common;

import me.loggits.mountain.common.adapters.ConfigAdapter;
import me.loggits.mountain.common.adapters.SchedulerAdapter;

import java.util.logging.Logger;

public interface MountainBootstrapper {

    Logger getPluginLogger();

    SchedulerAdapter getScheduler();

    ConfigAdapter getConfiguration();

    String getVersion();

    void disable();

}
