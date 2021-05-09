package me.loggits.mountain.common.adapters;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public interface ConfigAdapter {

    void save();

    void load() throws Exception;

    FileConfiguration loadConfig(File file) throws Exception;

    void reloadConfigs();

    List<String> getStringList(String path, List<String> defaults);

    void set(String path, Object value);

    File generateFile(String sub, String filename);

    void setup(String sub, String fileName);

    void createConfigFiles(String sub, String fileName);

    void copyDefaults();

    Set<String> getKeys(boolean deep);

    ConfigurationSection getConfigurationSection(String s);

    default void log(String message) {
        log(Level.INFO, message);
    }

    void log(Level level, String message);

}
