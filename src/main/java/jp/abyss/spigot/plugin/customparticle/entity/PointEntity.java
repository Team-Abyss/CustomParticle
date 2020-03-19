package jp.abyss.spigot.plugin.customparticle.entity;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Point;
import org.bukkit.Location;
import org.bukkit.Particle;

public class PointEntity implements Point {

    private Location center;
    private Particle particle;

    public PointEntity(Location center, Particle particle) {
        this.center = center;
        this.particle = particle;
    }

    @Override
    public Location getLocation() {
        return center.clone();
    }

    @Override
    public Particle getParticle() {
        return particle;
    }

    @Override
    public boolean spawn() {
        center.getWorld().spawnParticle(particle, center, 0, 0, 0, 0);
        return true;
    }
}
