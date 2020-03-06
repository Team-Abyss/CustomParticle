package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface DrawThreeDimensionCircleAble extends DrawPointAble{
    void drawThreeDimensionCircle(Particle particle, Location location,double radius,int point);
}
