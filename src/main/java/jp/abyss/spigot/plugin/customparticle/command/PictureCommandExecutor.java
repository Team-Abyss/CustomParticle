package jp.abyss.spigot.plugin.customparticle.command;

import jp.abyss.spigot.plugin.customparticle.CustomParticle;
import jp.abyss.spigot.plugin.customparticle.api.CustomParticleAPI;
import jp.abyss.spigot.plugin.customparticle.api.ParticleManager;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Picture;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PictureCommandExecutor extends CommandBaseExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isConsoleCommandSender(sender)) {
            sender.sendMessage("プレイヤー、コマンドブロック専用コマンドです。");
            return true;
        }
        CustomParticleAPI api = CustomParticle.getCustomParticleAPI();
        ParticleManager manager = api.getParticleManager();
        Location senderLocation;
        Location cornerLocation;
        Picture picture;
        float step;


        if ((senderLocation = getSenderLocation(sender)) == null) {
            return false;
        }
        try {
            switch (args.length) {
                case 0:
                    sender.sendMessage(ChatColor.RED + "使用法:/picture <名前> [x] [y] [z] [yaw] [pitch] [間隔] [横のピクセル数]");
                    return false;
                case 1:
                    picture = manager.createPicture(args[0], senderLocation);
                    break;
                case 4:
                    cornerLocation = convertStringToLocation(new String[]{args[1], args[2], args[3]}, senderLocation);
                    cornerLocation.setYaw(senderLocation.getYaw());
                    cornerLocation.setPitch(senderLocation.getPitch());
                    picture = manager.createPicture(args[0], cornerLocation);
                    break;
                case 6:
                    cornerLocation = convertStringToLocation(new String[]{args[1], args[2], args[3], args[4], args[5]}, senderLocation);
                    picture = manager.createPicture(args[0], cornerLocation);
                    break;
                case 7:
                    cornerLocation = convertStringToLocation(new String[]{args[1], args[2], args[3], args[4], args[5]}, senderLocation);
                    step = Float.parseFloat(args[6]);
                    picture = manager.createPicture(args[0], cornerLocation, null, step);
                    break;
                case 8:
                    cornerLocation = convertStringToLocation(new String[]{args[1], args[2], args[3], args[4], args[5]}, senderLocation);
                    step = Float.parseFloat(args[6]);
                    Integer width = Integer.parseInt(args[7]);
                    picture = manager.createPicture(args[0], cornerLocation, width, step);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "パラメーターが不正です。");
                    return false;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("正確な値を入力してください。");
            return false;
        }
        if (!picture.spawn()) {
            sender.sendMessage(ChatColor.RED + "そのような画像ファイルは存在しません:" + args[0]);
            return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            try {
                Arrays.stream(Objects.requireNonNull(CustomParticle.getPlugin().getDataFolder().listFiles())).forEach(file -> {
                    if (file.isFile() && file.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                        list.add(file.getName());
                    }
                });
            } catch (NullPointerException ignored) {
            }
        } else if (isPlayer(sender)) {
            Location location = parsePlayer(sender).getLocation();
            switch (args.length) {
                case 2:
                    list.add(String.valueOf(location.getX()));
                    break;
                case 3:
                    list.add(String.valueOf(location.getY()));
                    break;
                case 4:
                    list.add(String.valueOf(location.getZ()));
                    break;
                case 5:
                    list.add(String.valueOf(location.getYaw()));
                    break;
                case 6:
                    list.add(String.valueOf(location.getPitch()));
                    break;
            }
        }
        return list;
    }

}
