package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.core.ParticleOperator;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

class ParticleCore implements ParticleOperator {

    @Override
    public void drawCircle(Particle particle, Location center, double radius, double quantity) {
        center.setYaw(0);
        center.setPitch(0);
        drawThreeDimensionCircle(particle, center, radius, quantity);
    }

    @Override
    public void drawThreeDimensionCircle(Particle particle, Location location, double radius, double point) {
        double rYaw = Math.toRadians(location.getYaw());
        Vector v1 = location.getDirection();

        Vector v2 = new Vector(Math.cos(rYaw), 0, Math.sin(rYaw));
        //角速度ω
        double i = 2 * Math.PI / point;

        for (int count = 0; count < point; count++) {

            double angle = i * count;
            double x = radius * v1.getX() * Math.cos(angle) + radius * v2.getX() * Math.sin(angle);
            double y = radius * v1.getY() * Math.cos(angle) + radius * v2.getY() * Math.sin(angle);
            double z = radius * v1.getZ() * Math.cos(angle) + radius * v2.getZ() * Math.sin(angle);
            location.add(x, y, z);
            drawPoint(particle, location);
            location.subtract(x, y, z);
        }
    }

    @Override
    public void drawTriangle(Particle particle, Location center, double radius, double space, double first) {
        center.setYaw((float) first);
        drawPolygon(particle, center, radius, 3, space);
    }

    @Override
    public void drawPolygon(Particle particle, Location location, double radius, int count, double space) {
        if (count == 0) return;

        double t0 = Math.toRadians(location.getYaw());
        double t = t0;
        double i = 2 * Math.PI / count;
        Location location2 = location.clone();

        for (int innerCount = 0; innerCount < count; ) {
            double x = -radius * Math.sin(t);
            double z = radius * Math.cos(t);
            location.add(x, 0, z);
            t += i;
            innerCount++;

            double x2 = -radius * Math.sin(t);
            double z2 = radius * Math.cos(t);
            if (innerCount == count) {
                x2 = -radius * Math.sin(t0);
                z2 = radius * Math.cos(t0);
            }
            location2.add(x2, 0, z2);
            drawLine(particle, location, location2, space);

            location.subtract(x, 0, z);
            location2.subtract(x2, 0, z2);

        }
    }


    @Override
    public void drawLine(Particle particle, Location from, Location to, double space) {
        World world = from.getWorld();
        Validate.isTrue(to.getWorld().equals(world), "同じワールド同士でのみ使用可能です");
        double distance = from.distance(to);
        Vector p1 = from.toVector();
        Vector p2 = to.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double covered = 0;
        for (; covered < distance; p1.add(vector)) {
            drawPoint(particle, p1.toLocation(world));
            covered += space;
        }
    }

    @Override
    public void drawPoint(Particle particle, Location to) {
        World world = to.getWorld();
        world.spawnParticle(particle, to.getX(), to.getY(), to.getZ(), 0, 0, 0, 0);
    }

    @Override
    public void drawCircle(Particle particle, Location center, double radius) {
        drawCircle(particle, center, radius, 16);
    }

    @Override
    public void drawTriangle(Particle particle, Location center, double radius, double space) {
        drawTriangle(particle, center, radius, space, 0);
    }

    private static List<PictureWriter> writers = new ArrayList<>();

    public static boolean drawPicture(String name,Location location){
        return drawPicture(name,location,null);
    }

    public static boolean drawPicture(String name,Location location,Integer width){
        PictureWriter dp = null;
        for (PictureWriter value: writers){
            if (value.getName().equals(name)){
                if (width == null || value.getWidth() == width){
                    dp = value;
                }
            }
        }
        if (dp == null){
            if (width > 0){
                dp = PictureWriter.getInstance(name,width);
            }else {
                dp = PictureWriter.getInstance(name);
            }
        }
        if (dp != null){
            dp.draw(location);
            return true;
        }else {
            return false;
        }
    }
}
