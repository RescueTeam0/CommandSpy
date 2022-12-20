package me.rescueteam.commandspy.commands;

import me.rescueteam.commandspy.CommandSpy;
import me.rescueteam.commandspy.files.DBConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

public class CSpyTabComplete implements TabCompleter {

    public CSpyTabComplete() {
        Objects.requireNonNull(CommandSpy.getIstance().getCommand("commandspy")).setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> COMMANDS = new ArrayList<>();

        for (SubCommand i : CSpyManager.getSubCommands()) {
            COMMANDS.add(i.getName());
            if (i.getAliases() != null) COMMANDS.addAll(i.getAliases());
        }

        if (sender.hasPermission("commandspy.use")) {
            if (args.length == 1) {
                List<String> completions = new ArrayList<>();
                StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
                Collections.sort(completions);
                return completions;
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")) {
                    List<String> completions = new ArrayList<>();
                    List<String> onlinePlayers = new ArrayList<>();
                    List<String> list = (List<String>) DBConfig.get().getList("enabled-players");
                    assert list != null;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        onlinePlayers.add(p.getName());
                    }
                    for (String i : list) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(i));
                        onlinePlayers.remove(offlinePlayer.getName());
                    }
                    StringUtil.copyPartialMatches(args[1], onlinePlayers, completions);
                    Collections.sort(completions);
                    return completions;
                } else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")) {
                    List<String> completions = new ArrayList<>();
                    List<String> playersToShow = new ArrayList<>();
                    List<String> list = (List<String>) DBConfig.get().getList("enabled-players");
                    assert list != null;
                    for (String i : list) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(i));
                        if (offlinePlayer.isOnline()) playersToShow.add(offlinePlayer.getName());
                    }
                    StringUtil.copyPartialMatches(args[1], playersToShow, completions);
                    Collections.sort(completions);
                    return completions;
                }
            }
        }

        return null;
    }
}
