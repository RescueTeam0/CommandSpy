package me.rescueteam.commandspy.commands.subcommands;

import me.rescueteam.commandspy.CommandSpy;
import me.rescueteam.commandspy.commands.SubCommand;
import me.rescueteam.commandspy.utils.CommandListUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class HelpSubCommand extends SubCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return CommandSpy.getIstance().getConfig().getString("help-desc");
    }

    @Override
    public String getSyntax() {
        return "/commandspy help";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void perform(Player player, String[] args) {
        player.spigot().sendMessage(getHelpMenu());
    }

    public TextComponent getHelpMenu() {
        Plugin plugin = CommandSpy.getIstance();
        FileConfiguration config = plugin.getConfig();
        String[] subCmds = {"help", "reload", "enable", "disable", "enable <player>", "disable <player>"};
        String[] subCmdsDesc = {config.getString("help-desc"), config.getString("reload-desc"),
                config.getString("enable-desc"), config.getString("disable-desc"),
                config.getString("enable-other-desc"), config.getString("disable-other-desc")};
        return CommandListUtils.getList("commandspy", subCmds, subCmdsDesc, true);
    }

}
