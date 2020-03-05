package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface DrawLineAble extends DrawPointAble {

    void drawLine(Particle particle, Location from,Location to,double space);

}
