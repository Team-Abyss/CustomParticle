package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface CircleWriter extends ThreeDimensionCircleWriter {

    void drawCircle(Particle particle, Location center,double radius,int quantity);

    void drawCircle(Particle particle,Location center,double radius);

}
