package me.rescueteam.commandspy.commands.subcommands;

import me.rescueteam.commandspy.CommandSpy;
import me.rescueteam.commandspy.commands.SubCommand;
import me.rescueteam.commandspy.files.DBConfig;
import me.rescueteam.commandspy.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReloadSubCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return CommandSpy.getIstance().getConfig().getString("reload-desc");
    }

    @Override
    public String getSyntax() {
        return "/commandspy reload";
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>();
    }

    @Override
    public void perform(Player player, String[] args) {
        FileConfiguration config = CommandSpy.getIstance().getConfig();
        if (player.hasPermission("commandspy.reload")) {
            long time = System.currentTimeMillis();
            CommandSpy.getIstance().reloadConfig();
            DBConfig.reload();
            time = System.currentTimeMillis() - time;
            player.sendMessage(ColorUtils.colorize(config.getString("reloaded"))
                    .replace("%time%", String.valueOf(time)));
        } else {
            player.sendMessage(ColorUtils.colorize(config.getString("no-permission")));
        }
    }
}
