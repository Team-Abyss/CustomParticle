package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface PointWriter {

    void drawPoint(Particle particle, Location to);

}
