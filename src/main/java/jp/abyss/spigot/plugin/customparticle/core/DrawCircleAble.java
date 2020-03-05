package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface DrawCircleAble extends DrawThreeDimensionCircleAble {

    void drawCircle(Particle particle, Location center,double radius,double quantity);

    void drawCircle(Particle particle,Location center,double radius);

}
