package me.rescueteam.commandspy;

import me.rescueteam.commandspy.commands.CSpyManager;
import me.rescueteam.commandspy.commands.CSpyTabComplete;
import me.rescueteam.commandspy.files.DBConfig;
import me.rescueteam.commandspy.listeners.CommandListener;
import me.rescueteam.commandspy.utils.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings("unused")
public final class CommandSpy extends JavaPlugin {

    private static CommandSpy istance;

    @Override
    public void onEnable() {
        istance = this;
        new CSpyManager();
        new CSpyTabComplete();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        DBConfig.setup();
        DBConfig.get().addDefault("enabled-players", new ArrayList<UUID>());
        DBConfig.get().options().copyDefaults(true);
        DBConfig.save();
        int pluginId = 17125;
        Metrics metrics = new Metrics(this, pluginId);
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        System.out.println("§7[CommandSpy] Plugin successfully §aenabled");
    }

    @Override
    public void onDisable() {
        System.out.println("§7[CommandSpy] Plugin successfully §cdisabled");
    }

    public static CommandSpy getIstance() {
        return istance;
    }

}
