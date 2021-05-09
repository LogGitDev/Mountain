package me.loggits.mountain.bukkit;

import com.google.common.io.Files;
import me.loggits.mountain.common.TextHandler;
import me.loggits.mountain.common.adapters.ConfigAdapter;
import me.loggits.mountain.common.utils.DataTypeChecks;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class BukkitConfig implements ConfigAdapter {
    private static FileConfiguration config;
    String fileName;
    String sub;
    boolean apply;
    private File file;
    private JavaPlugin plugin;

    public BukkitConfig(JavaPlugin plugin, String fileName, boolean apply) {
        this(plugin, "", fileName, apply);
    }

    public BukkitConfig(JavaPlugin plugin, File file, boolean apply) {
        this(plugin, file.getParent(), file.getName(), apply);
    }

    public BukkitConfig(JavaPlugin plugin, String subFolder, String fileName, boolean apply) {
        this.sub = subFolder;
        this.fileName = fileName;
        this.apply = apply;
        this.plugin = plugin;
        setup(subFolder, fileName);
    }

    @Override
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            log(Level.WARNING, "Unable to save: " + file.getName() + "!");
        }
    }

    @Override
    public void load() throws Exception {
        config = loadConfig(file);
    }

    @Override
    public FileConfiguration loadConfig(File file) throws Exception {
        YamlConfiguration config = new YamlConfiguration();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            config.load(isr);
            isr.close();
        } catch (FileNotFoundException ignored) {
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
            Files.copy(
                    file,
                    generateFile(
                            "/backups/",
                            String.format("%s-%d.yml", file.getName().replace(".yml", ""), System.currentTimeMillis())
                    )
            );
            throw e;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }

    @Override
    public void reloadConfigs() {
        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        save();
    }

    public String get(String path, String def) {
        throw new UnsupportedOperationException("retrieving data is yet to be setup");
    }

    public Long get(String path, Long def) {
        throw new UnsupportedOperationException("retrieving data is yet to be setup");
    }

    public Boolean get(String path, Boolean def) {
        throw new UnsupportedOperationException("retrieving data is yet to be setup");
    }

    public Double get(String path, Double def) {
        throw new UnsupportedOperationException("retrieving data is yet to be setup");
    }

    public Integer get(String path, Integer def) {
        throw new UnsupportedOperationException("retrieving data is yet to be setup");
    }

    @Override
    public List<String> getStringList(String path, List<String> defaults) {
        List<?> list = config.getList(path, defaults);
        if (list == null) {
            return new ArrayList<>(0);
        }

        List<String> result = new ArrayList<>();
        for (Object object : list) {
            if ((object instanceof String) || (DataTypeChecks.isPrimitiveWrapper(object))) {
                result.add(TextHandler.translateString(String.valueOf(object)));
            }
        }

        return result;
    }

    @Override
    public void set(String path, Object value) {
        config.set(path, value);
    }

    @Override
    public File generateFile(String sub, String filename) {
        File file = new File(plugin.getDataFolder() + sub, filename);
        if (sub != null && !sub.isEmpty()) {
            File f = new File(plugin.getDataFolder() + sub);
            if (!f.exists()) {
                if (!f.mkdirs()) log("Unable to create BACKUP folder");
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log(Level.WARNING, String.format("AN ERROR OCCURRED: Unable to create, %s!", filename));
            }
            log(String.format("Created file: %s!", filename));
        }
        return file;
    }

    @Override
    public void setup(String sub, String filename) {
        if (!plugin.getDataFolder().exists() && !plugin.getDataFolder().mkdir()) {
            log(String.format("Unable to create %s folder for %s.", sub, filename));
        }
        createConfigFiles(sub, filename);
        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        copyDefaults();
        save();
    }

    @Override
    public void createConfigFiles(String sub, String filename) {
        file = generateFile(sub, filename);
    }

    @Override
    public void copyDefaults() {
        config.options().copyDefaults(true);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String section) {
        return config.getConfigurationSection(section);
    }

    @Override
    public void log(Level level, String message) {
        plugin.getLogger().log(level, message);
    }
}
