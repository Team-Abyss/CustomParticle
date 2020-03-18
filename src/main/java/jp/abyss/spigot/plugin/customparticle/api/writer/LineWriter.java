package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;
import org.bukkit.Particle;

public interface LineWriter extends PointWriter {

    void drawLine(Particle particle, Location from,Location to,double space);

}
