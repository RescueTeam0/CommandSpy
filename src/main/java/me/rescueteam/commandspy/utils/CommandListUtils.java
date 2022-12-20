package me.rescueteam.commandspy.utils;

import me.rescueteam.commandspy.CommandSpy;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class CommandListUtils {

    static Plugin plugin = CommandSpy.getPlugin(CommandSpy.class);

    public static TextComponent getList(String commandName, String[] subCommands, String[] subCommandsDescription, boolean includeBaseCommand) {
        FileConfiguration config = plugin.getConfig();
        TextComponent base = new TextComponent(TextComponent.fromLegacyText(ColorUtils.colorize("")));
        String prefix = ColorUtils.colorize(" &8| ");
        TextComponent title = new TextComponent(TextComponent.fromLegacyText(ColorUtils.colorize(prefix + config.getString("help-title")).replace("%command%", commandName)));
        base.addExtra("\n");
        base.addExtra(title);
        for (int i = 0; i < subCommands.length; i++) {
            TextComponent command;
            if (includeBaseCommand) {
                command = new TextComponent(TextComponent.fromLegacyText(ColorUtils.colorize(ChatColor.AQUA + "/" + commandName + " " + subCommands[i])));
                command.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ColorUtils.colorize(ChatColor.AQUA + "/" + commandName + " " + subCommands[i] + "\n" + ChatColor.GRAY + subCommandsDescription[i]))));
                command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + commandName + " " + subCommands[i]));
            } else {
                command = new TextComponent(TextComponent.fromLegacyText(ColorUtils.colorize(ChatColor.AQUA + "/" + subCommands[i])));
                command.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ColorUtils.colorize(ChatColor.AQUA + "/" + subCommands[i] + "\n" + ChatColor.GRAY + subCommandsDescription[i]))));
                command.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + subCommands[i]));
            }
            base.addExtra("\n");
            base.addExtra(prefix);
            base.addExtra(command);
        }
        base.addExtra("\n");
        TextComponent footer = new TextComponent(TextComponent.fromLegacyText(ColorUtils.colorize(config.getString("help-footer"))));
        footer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ColorUtils.colorize("&7CommandSpy &fv" + plugin.getDescription().getVersion() + " &7by RescueTeam"))));
        base.addExtra(prefix);
        base.addExtra(footer);
        base.addExtra("\n");
        return base;
    }

}
