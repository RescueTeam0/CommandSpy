package me.rescueteam.commandspy.commands;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommand {

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract List<String> getAliases();

    public abstract void perform(Player player, String[] args);

}
