package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface DrawThreeDimensionPolygonAble {
    void drawThreeDimensionPolygon(Particle particle, Location location, double radius, int count, double space);
}
