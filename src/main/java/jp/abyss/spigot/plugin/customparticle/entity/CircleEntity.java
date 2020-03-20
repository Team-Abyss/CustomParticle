package jp.abyss.spigot.plugin.customparticle.entity;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Circle;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.ThreeDimensionCircle;
import org.bukkit.Location;

public class CircleEntity extends PointAggregationEntity implements Circle, ThreeDimensionCircle {

    private double radius;
    private int quantity;
    private Location center;

    public CircleEntity(Location center, double radius, int quantity) {
        this.center = center;
        this.quantity = quantity;
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Location getCenter() {
        return center.clone();
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

}
