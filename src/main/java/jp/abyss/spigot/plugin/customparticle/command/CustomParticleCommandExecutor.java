package jp.abyss.spigot.plugin.customparticle.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.*;

public class CustomParticleCommandExecutor extends CommandExecutor {

    private Map<String, CommandExecutor> subCommand = new HashMap<>();

    public CustomParticleCommandExecutor(){
        subCommand.put("point",new PointCommandExecutor());
        subCommand.put("line",new LineCommandExecutor());
        subCommand.put("circle",new CircleCommandExecutor());
        subCommand.put("polygon",new PolygonCommandExecutor());
        subCommand.put("triangle",new TriangleCommandExecutor());
    }

    private boolean help(CommandSender sender){
        sender.sendMessage( ChatColor.AQUA+"ーーーーーーーーーCustomParticleーーーーーーーーー\n"+
                ChatColor.AQUA+" /customparticle(cp) point      :点を描画"+
                ChatColor.AQUA+" /customparticle(cp) line　     :線を描画" +
                ChatColor.AQUA+" /customparticle(cp) circle     :円を描画"+
                ChatColor.AQUA+" /customparticle(cp) polygon    :多角形を描画"+
                ChatColor.AQUA+" /customparticle(cp) triangle   :三角形を描画");
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) return help(sender);

        if(args[0].toLowerCase().equals("help")) return help(sender);

        if(subCommand.get(args[0].toLowerCase()) == null){
            return wrongCommand(sender,"/customparticle(cp) help");
        }else {
            return subCommand.get(args[0].toLowerCase()).onCommand(sender, command, label, args);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1){
            if(args[0].length() == 0) return new ArrayList<>(subCommand.keySet());
            else return isSubCommand(subCommand.keySet(),args[0]);
        } else if(args.length == 2) return subCommand.get(args[0]).onTabComplete(sender, command, alias, args);
        return null;
    }
}
