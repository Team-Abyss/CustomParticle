package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.api.CustomParticleAPI;
import jp.abyss.spigot.plugin.customparticle.api.ParticleManager;
import jp.abyss.spigot.plugin.customparticle.command.CustomParticleCommandExecutor;
import jp.abyss.spigot.plugin.customparticle.command.PictureCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomParticle extends JavaPlugin implements CustomParticleAPI {

    public static CustomParticleAPI getCustomParticleAPI() {
        return (CustomParticleAPI) getPlugin();
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("CustomParticle");
    }

    @Override
    public ParticleManager getParticleManager() {
        return SimpleParticleManager.getParticleWriter();
    }

    @Override
    public void onEnable() {
        getLogger().info("Booting CustomParticle");
        getCommand("customparticle").setExecutor(new CustomParticleCommandExecutor());
        getCommand("picture").setExecutor(new PictureCommandExecutor());
    }

    @Override
    public void onDisable() {
        getLogger().info("See You");
    }

}
