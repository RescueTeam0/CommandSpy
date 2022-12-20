package me.rescueteam.commandspy.commands.subcommands;

import me.rescueteam.commandspy.CommandSpy;
import me.rescueteam.commandspy.commands.SubCommand;
import me.rescueteam.commandspy.files.DBConfig;
import me.rescueteam.commandspy.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EnableSubCommand extends SubCommand {

    @Override
    public String getName() {
        return "enable";
    }

    @Override
    public String getDescription() {
        return CommandSpy.getIstance().getConfig().getString("enable-desc");
    }

    @Override
    public String getSyntax() {
        return "/commandspy enable [player]";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("on");
        return aliases;
    }

    @Override
    public void perform(Player player, String[] args) {
        FileConfiguration config = CommandSpy.getIstance().getConfig();
        List<String> list = (List<String>) DBConfig.get().getList("enabled-players");
        assert list != null;
        if (args.length > 1) {
            if (player.hasPermission("commandspy.use.other")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (!list.contains(target.getUniqueId().toString())) {
                        list.add(target.getUniqueId().toString());
                        DBConfig.save();
                        DBConfig.reload();
                        player.sendMessage(ColorUtils.colorize(config.getString("enabled-other"))
                                .replace("%target%", target.getName()));
                        target.sendMessage(ColorUtils.colorize(config.getString("enabled")));
                    } else {
                        player.sendMessage(ColorUtils.colorize(config.getString("already-enabled-other"))
                                .replace("%target%", target.getName()));
                    }
                } else {
                    player.sendMessage(ColorUtils.colorize(config.getString("player-not-found")));
                }
            } else {
                player.sendMessage(ColorUtils.colorize(config.getString("no-permission")));
            }
        } else {
            if (!list.contains(player.getUniqueId().toString())) {
                list.add(player.getUniqueId().toString());
                DBConfig.save();
                DBConfig.reload();
                player.sendMessage(ColorUtils.colorize(config.getString("enabled")));
            } else {
                player.sendMessage(ColorUtils.colorize(config.getString("already-enabled")));
            }
        }
    }
}
