package jp.abyss.spigot.plugin.customparticle.command;

import jp.abyss.spigot.plugin.customparticle.CustomParticle;
import jp.abyss.spigot.plugin.customparticle.api.ParticleManager;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Line;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.ThreeDimensionCircle;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.ThreeDimensionPolygon;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomParticleCommandExecutor extends CommandBaseExecutor {

    private ParticleManager manager = CustomParticle.getCustomParticleAPI().getParticleManager();
    private Location senderLocation = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isConsoleCommandSender(sender)) {
            sender.sendMessage(ChatColor.RED + "プレイヤー、コマンドブロック専用コマンドです。");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "使用法:/customparticle[cp] <circle|line|polygon> ...");
            return false;
        }
        senderLocation = getSenderLocation(sender);
        switch (args[0]) {
            case "circle":
                return circle(sender, args);
            case "line":
                return line(sender, args);
            case "polygon":
                return polygon(sender, args);
            default:
                sender.sendMessage(ChatColor.RED + "使用法:/customparticle[cp] <circle|line|polygon> ...");
                return false;
        }
    }

    private boolean circle(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "使用法:/customparticle[cp] circle <名前> <半径> [x] [y] [z] [yaw] [pitch] [点の数]");
            return false;
        }

        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "パラメーターが不足しています。");
            return false;
        }

        ThreeDimensionCircle threeDimensionCircle;
        Particle particle;
        double radius;
        int quantity = 32;
        Location center;

        try {
            particle = Particle.valueOf(args[1].toUpperCase());
            radius = Double.parseDouble(args[2]);

            switch (args.length) {
                case 3:
                    center = senderLocation;
                    break;
                case 9:
                    quantity = Integer.parseInt(args[8]);
                case 8:
                    center = convertStringToLocation(new String[]{args[3], args[4], args[5], args[6], args[7]}, senderLocation);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "パラメーターが不正です。");
                    return false;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "正確な値を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "指定パーティクルは存在しません。");
            return false;
        }
        threeDimensionCircle = manager.createThreeDimensionCircle(particle, center, radius, quantity);
        return threeDimensionCircle.spawn();
    }

    private boolean line(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "使用法:/customparticle[cp] line <名前> <x1> <y1> <z1> <x2> <y2> <z2> [間隔]");
            return false;
        }

        if (args.length < 8) {
            sender.sendMessage(ChatColor.RED + "パラメーターが不足しています。");
            return false;
        } else if (args.length > 9) {
            sender.sendMessage(ChatColor.RED + "パラメーターが不正です。");
            return false;
        }

        Line line;
        Particle particle;
        Location from;
        Location to;
        double space = 0.2;

        try {
            particle = Particle.valueOf(args[1].toUpperCase());
            from = convertStringToLocation(new String[]{args[2], args[3], args[4]}, senderLocation);
            to = convertStringToLocation(new String[]{args[5], args[6], args[7]}, senderLocation);
            if (args.length == 9) space = Double.parseDouble(args[8]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "正確な値を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "指定パーティクルは存在しません。");
            return false;
        }
        line = manager.createLine(particle, from, to, space);
        return line.spawn();
    }

    private boolean polygon(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "使用法:/customparticle[cp] polygon <名前> <半径> <頂点の数> [x] [y] [z] [yaw] [pitch] [間隔]");
            return false;
        }

        if (args.length < 4) {
            sender.sendMessage(ChatColor.RED + "パラメーターが不足しています。");
            return false;
        }

        ThreeDimensionPolygon polygon;
        Particle particle;
        double radius;
        int quantity;
        Location center;
        double space = 0.2;

        try {
            particle = Particle.valueOf(args[1].toUpperCase());
            radius = Double.parseDouble(args[2]);
            quantity = Integer.parseInt(args[3]);

            switch (args.length) {
                case 4:
                    center = senderLocation;
                    break;
                case 10:
                    space = Double.parseDouble(args[9]);
                case 9:
                    center = convertStringToLocation(new String[]{args[4], args[5], args[6], args[7], args[8]}, senderLocation);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "パラメーターが不正です。");
                    return false;
            }

        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "正確な値を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "指定パーティクルは存在しません。");
            return false;
        }
        polygon = manager.createThreeDimensionPolygon(particle, center, radius, quantity, space);
        return polygon.spawn();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (isConsoleCommandSender(sender)) return list;
        senderLocation = getSenderLocation(sender);
        switch (args.length) {
            case 1:
                if ("circle".startsWith(args[0].toLowerCase())) list.add("circle");
                if ("polygon".startsWith(args[0].toLowerCase())) list.add("polygon");
                if ("line".startsWith(args[0].toLowerCase())) list.add("line");
                break;
            case 2:
                Arrays.stream(Particle.values()).forEach(particle -> {
                    if (particle.name().toLowerCase().startsWith(args[1].toLowerCase())) {
                        list.add(particle.name().toLowerCase());
                    }
                });
                break;
            case 3:
                if (args[0].equalsIgnoreCase("line")) list.add(String.valueOf(senderLocation.getX()));
                break;
            case 4:
                if (args[0].equalsIgnoreCase("circle")) list.add(String.valueOf(senderLocation.getX()));
                if (args[0].equalsIgnoreCase("line")) list.add(String.valueOf(senderLocation.getY()));
                break;
            case 5:
                if (args[0].equalsIgnoreCase("line")) list.add(String.valueOf(senderLocation.getZ()));
                if (args[0].equalsIgnoreCase("circle")) list.add(String.valueOf(senderLocation.getY()));
                if (args[0].equalsIgnoreCase("polygon")) list.add(String.valueOf(senderLocation.getX()));
                break;
            case 6:
                if (args[0].equalsIgnoreCase("line")) list.add(String.valueOf(senderLocation.getX()));
                if (args[0].equalsIgnoreCase("circle")) list.add(String.valueOf(senderLocation.getZ()));
                if (args[0].equalsIgnoreCase("polygon")) list.add(String.valueOf(senderLocation.getY()));
                break;
            case 7:
                if (args[0].equalsIgnoreCase("line")) list.add(String.valueOf(senderLocation.getY()));
                if (args[0].equalsIgnoreCase("polygon")) list.add(String.valueOf(senderLocation.getZ()));
                break;
            case 8:
                if (args[0].equalsIgnoreCase("line")) list.add(String.valueOf(senderLocation.getZ()));
                break;
        }
        return list;
    }

}
