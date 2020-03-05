package jp.abyss.spigot.plugin.customparticle.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class CommandExecutor implements org.bukkit.command.CommandExecutor, TabExecutor {
    protected boolean isPlayer(CommandSender sender){
        return sender instanceof Player;
    }

    protected boolean isBlockCommandSender(CommandSender sender){
        return sender instanceof BlockCommandSender;
    }

    protected boolean isConsoleSender(CommandSender sender){
        return sender instanceof ConsoleCommandSender;
    }

    protected boolean wrongCommand(CommandSender sender,String helpCommandKey){
        sender.sendMessage("コマンドに間違いがあります コマンドのヘルプは"+helpCommandKey+"です" );
        return true;
    }

    protected List<String> isSubCommand(Set<String> subCommand, String arg){
        List<String> tabList = new ArrayList<>();
        for(String label:subCommand){
            if(label.startsWith(arg)) tabList.add(label);
        }
        return tabList;
    }

}
