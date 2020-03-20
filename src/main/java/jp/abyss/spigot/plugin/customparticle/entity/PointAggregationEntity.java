package jp.abyss.spigot.plugin.customparticle.entity;

import jp.abyss.spigot.plugin.customparticle.api.entity.PointAggregation;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class PointAggregationEntity implements PointAggregation {

    private List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }

    @Override
    public boolean spawn() {
        boolean t = true;
        for (Point point : points) {
            if (!point.spawn()) t = false;
        }
        return t;
    }
}
