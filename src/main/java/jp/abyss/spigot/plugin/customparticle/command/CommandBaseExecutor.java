package jp.abyss.spigot.plugin.customparticle.command;

import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public abstract class CommandBaseExecutor implements TabExecutor {

    protected boolean isPlayer(CommandSender commandSender) {
        return commandSender instanceof Player;
    }

    protected boolean isConsoleCommandSender(CommandSender commandSender) {
        return commandSender instanceof ConsoleCommandSender;
    }

    protected boolean isBlockCommandSender(CommandSender commandSender) {
        return commandSender instanceof BlockCommandSender;
    }

    protected Player parsePlayer(CommandSender commandSender) throws IllegalStateException {
        if (isPlayer(commandSender)) {
            return (Player) commandSender;
        } else {
            throw new IllegalStateException();
        }
    }

    protected ConsoleCommandSender parseConsoleCommandSender(CommandSender commandSender) throws IllegalStateException {
        if (isConsoleCommandSender(commandSender)) {
            return (ConsoleCommandSender) commandSender;
        } else {
            throw new IllegalStateException();
        }
    }

    protected BlockCommandSender parseBlockCommandSender(CommandSender commandSender) throws IllegalStateException {
        if (isBlockCommandSender(commandSender)) {
            return (BlockCommandSender) commandSender;
        } else {
            throw new IllegalStateException();
        }
    }

    protected Location convertStringToLocation(String[] params, Location origin) {
        Location location = origin.clone();
        if (params.length == 5) {
            location.setX(replaceString(params[0], location.getX()));
            location.setY(replaceString(params[1], location.getY()));
            location.setZ(replaceString(params[2], location.getZ()));
            location.setYaw((float) replaceString(params[3], location.getYaw()));
            location.setPitch((float) replaceString(params[4], location.getPitch()));
        } else if (params.length == 3) {
            location.setX(replaceString(params[0], location.getX()));
            location.setY(replaceString(params[1], location.getY()));
            location.setZ(replaceString(params[2], location.getZ()));
        } else {
            throw new IllegalArgumentException();
        }
        return location;
    }

    private double replaceString(String word, double origin) {
        if (word.startsWith("~")) {
            if (word.equalsIgnoreCase("~")) return origin;
            return (origin + Double.parseDouble(word.replace("~", "")));
        } else {
            return Double.parseDouble(word);
        }
    }

    protected Location getSenderLocation(CommandSender sender) {
        Location senderLocation;
        if (isPlayer(sender)) {
            senderLocation = parsePlayer(sender).getLocation();
        } else if (isBlockCommandSender(sender)) {
            senderLocation = parseBlockCommandSender(sender).getBlock().getLocation();
        } else {
            return null;
        }
        return senderLocation;
    }

}
