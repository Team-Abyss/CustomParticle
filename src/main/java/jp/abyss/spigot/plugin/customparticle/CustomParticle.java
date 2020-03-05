package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.command.ParticleCommand;
import jp.abyss.spigot.plugin.customparticle.command.PictureCommand;
import jp.abyss.spigot.plugin.customparticle.core.ParticleOperator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomParticle extends JavaPlugin {

    public static CustomParticle getCustomParticle(){
        return (CustomParticle) Bukkit.getServer().getPluginManager().getPlugin("CustomParticle");
    }

    private ParticleOperator operator = new ParticleCore();

    public ParticleOperator getOperator() {
        return operator;
    }

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.AQUA +"Booting CustomParticle");
        getCommand("customeparticle").setExecutor(new ParticleCommand());
        getCommand("picture").setExecutor(new PictureCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.AQUA + "See You");
    }
}
