package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface DrawPolygonAble extends DrawLineAble {
    void drawPolygon(Particle particle, Location location, double radius, int count, double space);
}
