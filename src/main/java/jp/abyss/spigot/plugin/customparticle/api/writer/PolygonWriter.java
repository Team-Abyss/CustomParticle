package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface PolygonWriter extends LineWriter {
    void drawPolygon(Particle particle, Location location, double radius, int count, double space);
}
