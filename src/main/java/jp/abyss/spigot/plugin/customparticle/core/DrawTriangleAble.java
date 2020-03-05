package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface DrawTriangleAble extends DrawPolygonAble{
    void drawTriangle(Particle particle, Location center,double radius,double space,double first);

    void drawTriangle(Particle particle, Location center,double radius,double space);
}
