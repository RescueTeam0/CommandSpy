package me.rescueteam.commandspy.listeners;

import me.rescueteam.commandspy.CommandSpy;
import me.rescueteam.commandspy.files.DBConfig;
import me.rescueteam.commandspy.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;
import java.util.Objects;

public class CommandListener implements Listener {

    final CommandSpy plugin;

    public CommandListener(CommandSpy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        FileConfiguration config = plugin.getConfig();
        List<String> ignoredCommands = config.getStringList("ignored-commands");
        String finalCommand = e.getMessage().replace("/", "");
        if (!ignoredCommands.isEmpty()) {
            for (String command : ignoredCommands) {
                if (finalCommand.equalsIgnoreCase(command)) {
                    return;
                }
            }
            List<String> list = (List<String>) DBConfig.get().getList("enabled-players");
            assert list != null;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p == e.getPlayer()) continue;
                if (e.getPlayer().hasPermission("commandspy.bypass")) continue;
                if (!p.hasPermission("commandspy.see")) continue;
                if (!list.contains(p.getUniqueId().toString())) continue;
                p.sendMessage(ColorUtils.colorize(config.getString("format"))
                        .replace("%sender%", e.getPlayer().getName())
                        .replace("%command%", finalCommand));
            }
        }
    }

}
