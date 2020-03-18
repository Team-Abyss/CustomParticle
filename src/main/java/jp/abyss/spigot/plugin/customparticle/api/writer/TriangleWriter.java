package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface TriangleWriter extends PolygonWriter {
    void drawTriangle(Particle particle, Location center,double radius,double space,double first);

    void drawTriangle(Particle particle, Location center,double radius,double space);
}
