package jp.abyss.spigot.plugin.customparticle.entity;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Polygon;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.ThreeDimensionPolygon;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Triangle;
import org.bukkit.Location;

public class PolygonEntity extends PointAggregationEntity implements Polygon, ThreeDimensionPolygon, Triangle {

    private Location center;
    private double radius;
    private int quantity;
    private double space;

    public PolygonEntity(Location center, double radius, int quantity, double space) {
        this.center = center;
        this.radius = radius;
        this.quantity = quantity;
        this.space = space;
    }

    @Override
    public double getSpace() {
        return space;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Location getCenter() {
        return center;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

}
