package jp.abyss.spigot.plugin.customparticle.command

import jp.abyss.spigot.plugin.customparticle.*
import org.bukkit.ChatColor
import org.bukkit.command.*
import org.bukkit.command.CommandExecutor
import org.bukkit.entity.Player
import kotlin.text.toIntOrNull

class PictureCommand :CommandExecutor,TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        if (!(sender is BlockCommandSender || sender is Player || sender is ProxiedCommandSender)) {
            sender.sendMessage("${ChatColor.RED}プレイヤー、コマンドブロック専用コマンドです。")
            return false
        }
        val operator = CustomParticle.getCustomParticle().operator

        when (args.size) {
            0 -> {
                sender.sendMessage("${ChatColor.RED}使用法:/picture <名前> [x] [y] [z] [yaw] [pitch] [間隔] [横のピクセル数]")
                return false
            }

            1 -> {
                return if (!operator.drawPicture(args[0], sender.getLocation())) {
                    sender.sendMessage("${ChatColor.RED}そのような画像ファイルは存在しません:${args[0]}")
                    false
                } else {
                    true
                }
            }
            2, 3 -> {
                sender.sendMessage("${ChatColor.RED}x,y,zの入力が必要です。")
                return false
            }
            4 -> {
                val location = sender.getLocation()?.fromString(args[1], args[2], args[3])
                return if (location != null) {
                    location.yaw = sender.getLocation()!!.yaw
                    location.pitch = sender.getLocation()!!.pitch
                    if (!operator.drawPicture(args[0], location)) {
                        sender.sendMessage("${ChatColor.RED}そのような画像ファイルは存在しません:${args[0]}")
                        false
                    } else {
                        true
                    }
                } else {
                    sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                    false
                }
            }
            5 -> {
                sender.sendMessage("${ChatColor.RED}x,y,z,yaw,pitchの入力が必要です。")
                return false
            }
            6 -> {
                val location = sender.getLocation()?.fromString(args[1], args[2], args[3], args[4], args[5])
                return if (location != null) {
                    if (!operator.drawPicture(args[0], location)) {
                        sender.sendMessage("${ChatColor.RED}そのような画像ファイルは存在しません:${args[0]}")
                        false
                    } else {
                        true
                    }
                } else {
                    sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                    false
                }
            }
            7 -> {
                val location = sender.getLocation()?.fromString(args[1], args[2], args[3], args[4], args[5])
                return if (location != null) {
                    if (!operator.drawPicture(args[0], location,null,args[6].toFloatOrNull() ?: kotlin.run {
                                sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                                return false
                            })) {
                        sender.sendMessage("${ChatColor.RED}そのような画像ファイルは存在しません:${args[0]}")
                        false
                    } else {
                        true
                    }
                } else {
                    sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                    false
                }
            }
            else -> {
                val location = sender.getLocation()?.fromString(args[1], args[2], args[3], args[4], args[5])
                return if (location != null) {
                    if (!operator.drawPicture(args[0], location,args[7].toIntOrNull() ?: kotlin.run {
                                sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                                return false
                            }
                                    ,args[7].toFloatOrNull() ?: kotlin.run {
                                sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                                return false
                            })) {
                        sender.sendMessage("${ChatColor.RED}そのような画像ファイルは存在しません:${args[0]}")
                        false
                    } else {
                        true
                    }
                } else {
                    sender.sendMessage("${ChatColor.RED}正確な値を入力してください。")
                    false
                }
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        val list = mutableListOf<String>()
        if (args.size == 1){
            CustomParticle.getCustomParticle().dataFolder.listFiles()?.forEach {
                if (it.isFile && it.name.toLowerCase().startsWith(args[0].toLowerCase())){
                    list.add(it.name)
                }
            }
        }else if (sender is Player){
            when (args.size){
                2 ->{
                    list.add(getTargetX(sender)?.toString() ?: return emptyList())
                }
                3 ->{
                    list.add(getTargetY(sender)?.toString() ?: return emptyList())
                }
                4 ->{
                    list.add(getTargetZ(sender)?.toString() ?: return emptyList())
                }
                5 ->{
                    list.add(sender.location!!.yaw.toString())
                }
                6 ->{
                    list.add(sender.location!!.pitch.toString())
                }
            }
        }
        return list
    }

}