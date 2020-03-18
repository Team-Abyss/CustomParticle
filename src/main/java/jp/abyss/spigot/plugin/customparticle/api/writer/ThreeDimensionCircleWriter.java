package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface ThreeDimensionCircleWriter extends PointWriter {
    void drawThreeDimensionCircle(Particle particle, Location location,double radius,int point);
}
