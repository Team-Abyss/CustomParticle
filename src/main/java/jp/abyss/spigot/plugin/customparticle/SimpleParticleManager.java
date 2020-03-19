package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.api.ParticleManager;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.*;
import jp.abyss.spigot.plugin.customparticle.entity.*;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class SimpleParticleManager implements ParticleManager {

    private static ParticleManager particleWriter = null;

    private SimpleParticleManager() {
    }

    public static ParticleManager getParticleWriter() {
        if (particleWriter == null) {
            particleWriter = new SimpleParticleManager();
        }
        return particleWriter;
    }

    @Override
    public Picture createPicture(String name, Location location) {
        return createPicture(name, location, null);
    }

    @Override
    public Picture createPicture(String name, Location location, Integer width) {
        return createPicture(name, location, width, 0.2f);
    }

    @Override
    public Picture createPicture(String name, Location location, Integer width, Float step) {
        return new PictureEntity(name, location, width, step);
    }

    @Override
    public Circle createCircle(Particle particle, Location center, double radius, int quantity) {
        center.setYaw(0);
        center.setPitch(0);
        return createThreeDimensionCircle(particle, center, radius, quantity);
    }

    @Override
    public Circle createCircle(Particle particle, Location center, double radius) {
        return createCircle(particle, center, radius, 32);
    }

    @Override
    public ThreeDimensionCircle createThreeDimensionCircle(Particle particle, Location location, double radius, int point) {
        Double[] result = pointOfThreeDimensionCircle(location.getYaw(), location.getPitch(), radius, point);
        CircleEntity circleEntity = new CircleEntity(location, radius, point);

        for (int count = 0; count < point; count++) {
            location.add(result[count * 3], result[count * 3 + 1], result[count * 3 + 2]);
            circleEntity.addPoint(createPoint(particle, location));
            location.subtract(result[count * 3], result[count * 3 + 1], result[count * 3 + 2]);
        }
        return circleEntity;
    }

    @Override
    public ThreeDimensionPolygon createThreeDimensionPolygon(Particle particle, Location location, double radius, int count, double space) {
        if (count == 0) throw new IllegalArgumentException("頂点の数が不正です");

        Double[] result = pointOfThreeDimensionCircle(location.getYaw(), location.getPitch(), radius, count);
        PolygonEntity polygonEntity = new PolygonEntity(location, radius, count, space);
        Location location2 = location.clone();
        for (int innerCount = 0; innerCount < count; ) {
            double x = result[innerCount * 3];
            double y = result[innerCount * 3 + 1];
            double z = result[innerCount * 3 + 2];
            location.add(x, y, z);

            innerCount++;

            double x2;
            double y2;
            double z2;
            if (innerCount == count) {
                x2 = result[0];
                y2 = result[1];
                z2 = result[2];
            } else {
                x2 = result[innerCount * 3];
                y2 = result[innerCount * 3 + 1];
                z2 = result[innerCount * 3 + 2];
            }

            location2.add(x2, y2, z2);

            Line line = createLine(particle, location, location2, space);
            for (Point point : line.getPoints()) {
                polygonEntity.addPoint(point);
            }
            location.subtract(x, y, z);
            location2.subtract(x2, y2, z2);
        }
        return polygonEntity;
    }

    @Override
    public Triangle createTriangle(Particle particle, Location center, double radius, double space, double first) {
        center.setYaw((float) first);
        return (Triangle) createPolygon(particle, center, radius, 3, space);
    }

    @Override
    public Triangle createTriangle(Particle particle, Location center, double radius, double space) {
        return createTriangle(particle, center, radius, space, 0);
    }

    @Override
    public Polygon createPolygon(Particle particle, Location location, double radius, int count, double space) {
        location.setPitch(0);
        return createThreeDimensionPolygon(particle, location, radius, count, space);
    }

    @Override
    public Line createLine(Particle particle, Location from, Location to, double space) {
        World world = from.getWorld();
        Validate.isTrue(to.getWorld().equals(world), "同じワールド同士でのみ使用可能です");
        double distance = from.distance(to);
        Vector p1 = from.toVector();
        Vector p2 = to.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double covered = 0;

        LineEntity lineEntity = new LineEntity(createPoint(particle, from), createPoint(particle, to), space);

        for (; covered < distance; p1.add(vector)) {
            lineEntity.addPoint(createPoint(particle, p1.toLocation(world)));
            covered += space;
        }
        return lineEntity;
    }

    @Override
    public Point createPoint(Particle particle, Location center) {
        return new PointEntity(center, particle);
    }

    //x,y,zの順に格納
    private Double[] pointOfThreeDimensionCircle(float yaw, float pitch, double radius, int point) {
        Double[] result = new Double[point * 3];

        Location location = new Location(null, 0, 0, 0, yaw, pitch);
        double rYaw = Math.toRadians(location.getYaw());
        Vector v1 = location.getDirection();

        Vector v2 = new Vector(Math.cos(rYaw), 0, Math.sin(rYaw));
        //角速度ω
        double i = 2 * Math.PI / point;

        for (int count = 0; count < point; count++) {

            double angle = i * count;

            result[count * 3] = radius * v1.getX() * Math.cos(angle) + radius * v2.getX() * Math.sin(angle);
            result[count * 3 + 1] = radius * v1.getY() * Math.cos(angle) + radius * v2.getY() * Math.sin(angle);
            result[count * 3 + 2] = radius * v1.getZ() * Math.cos(angle) + radius * v2.getZ() * Math.sin(angle);

        }
        return result;
    }
}
