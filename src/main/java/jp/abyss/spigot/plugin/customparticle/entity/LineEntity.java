package jp.abyss.spigot.plugin.customparticle.entity;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Line;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Point;

public class LineEntity extends PointAggregationEntity implements Line {

    private Point from;
    private Point to;
    private double space;

    public LineEntity(Point from, Point to, double space) {
        this.from = from;
        this.to = to;
        this.space = space;
    }

    @Override
    public Point getFrom() {
        return from;
    }

    @Override
    public Point getTo() {
        return to;
    }

    @Override
    public double getSpace() {
        return space;
    }

}
