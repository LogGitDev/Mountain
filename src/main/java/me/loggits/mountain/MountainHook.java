package me.loggits.mountain;

import org.bukkit.plugin.java.JavaPlugin;

public class MountainHook {
    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    public void setInstance(JavaPlugin instance) {
        this.instance = instance;
    }
}
