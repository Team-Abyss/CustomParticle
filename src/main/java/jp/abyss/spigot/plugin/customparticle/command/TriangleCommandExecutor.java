package jp.abyss.spigot.plugin.customparticle.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TriangleCommandExecutor extends CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
