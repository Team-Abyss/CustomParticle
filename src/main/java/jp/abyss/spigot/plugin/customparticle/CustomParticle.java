package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.command.ParticleCommand;
import jp.abyss.spigot.plugin.customparticle.command.PictureCommand;
import jp.abyss.spigot.plugin.customparticle.core.ParticleOperator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomParticle extends JavaPlugin {

    public static CustomParticle getCustomParticle(){
        return (CustomParticle) Bukkit.getServer().getPluginManager().getPlugin("CustomParticle");
    }

    public static Plugin getPlugin(){
        return Bukkit.getPluginManager().getPlugin("CustomParticle");
    }

    private ParticleOperator operator = new ParticleCore();

    public ParticleOperator getOperator() {
        return operator;
    }

    @Override
    public void onEnable() {
        getLogger().info("Booting CustomParticle");
        getCommand("customparticle").setExecutor(new ParticleCommand());
        getCommand("picture").setExecutor(new PictureCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("See You");
    }
}
