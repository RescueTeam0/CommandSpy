package me.rescueteam.commandspy.files;

import me.rescueteam.commandspy.CommandSpy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DBConfig {

    private static File file;
    private static FileConfiguration customConfiguration;

    public static void setup() {
        file = new File(CommandSpy.getIstance().getDataFolder(), "db.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customConfiguration;
    }

    public static void save() {
        try {
            customConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        customConfiguration = YamlConfiguration.loadConfiguration(file);
    }

}
