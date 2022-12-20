package me.rescueteam.commandspy.commands;

import me.rescueteam.commandspy.CommandSpy;
import me.rescueteam.commandspy.commands.subcommands.DisableSubCommand;
import me.rescueteam.commandspy.commands.subcommands.EnableSubCommand;
import me.rescueteam.commandspy.commands.subcommands.HelpSubCommand;
import me.rescueteam.commandspy.commands.subcommands.ReloadSubCommand;
import me.rescueteam.commandspy.files.DBConfig;
import me.rescueteam.commandspy.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSpyManager implements CommandExecutor {

    private static final List<SubCommand> subCommands = new ArrayList<>();

    public CSpyManager() {
        Objects.requireNonNull(CommandSpy.getIstance().getCommand("commandspy")).setExecutor(this);
        subCommands.add(new ReloadSubCommand());
        subCommands.add(new HelpSubCommand());
        subCommands.add(new EnableSubCommand());
        subCommands.add(new DisableSubCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = CommandSpy.getIstance().getConfig();

        if (sender instanceof Player p) {
            if (p.hasPermission("commandspy.use")) {
                if (args.length > 0) {
                    for (SubCommand i : subCommands) {
                        // Check aliases are used
                        if (i.getAliases() != null) {
                            for (String s : i.getAliases()) {
                                if (args[0].equalsIgnoreCase(s)) {
                                    i.perform(p, args);
                                    return true;
                                }
                                // Aliases of this SubCommand are not used
                            }
                        }
                        // Check if args[0] == SubCommand name
                        if (args[0].equalsIgnoreCase(i.getName())) {
                            i.perform(p, args);
                            return true;
                        }
                    }
                    new HelpSubCommand().perform(p, args);
                } else {
                    List<String> list = DBConfig.get().getStringList("enabled-players");
                    if (list.contains(p.getUniqueId().toString())) {
                        p.sendMessage(ColorUtils.colorize(config.getString("status-enabled")));
                    } else {
                        p.sendMessage(ColorUtils.colorize(config.getString("status-disabled")));
                    }
                }
            } else {
                p.sendMessage(ColorUtils.colorize(config.getString("default-message"))
                        .replace("%version%", CommandSpy.getIstance().getDescription().getVersion()));
            }
        }

        return true;
    }

    public static List<SubCommand> getSubCommands() {
        return subCommands;
    }

}
