package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.api.CustomParticleAPI;
import jp.abyss.spigot.plugin.customparticle.command.ParticleCommand;
import jp.abyss.spigot.plugin.customparticle.command.PictureCommand;
import jp.abyss.spigot.plugin.customparticle.api.ParticleWriter;
import jp.abyss.spigot.plugin.customparticle.writer.ImplParticleWriter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomParticle extends JavaPlugin implements CustomParticleAPI {

    public static CustomParticleAPI getCustomParticleAPI(){
        return (CustomParticleAPI) getPlugin();
    }

    public static Plugin getPlugin(){
        return Bukkit.getPluginManager().getPlugin("CustomParticle");
    }

    @Override
    public ParticleWriter getParticleWriter() {
        return ImplParticleWriter.getParticleWriter();
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
