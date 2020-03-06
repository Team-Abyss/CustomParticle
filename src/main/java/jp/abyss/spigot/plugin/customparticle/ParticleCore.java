package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.core.ParticleOperator;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class ParticleCore implements ParticleOperator {

    @Override
    public void drawCircle(Particle particle, Location center, double radius, int quantity) {
        center.setPitch(0);
        drawThreeDimensionCircle(particle, center, radius, quantity);
    }

    @Override
    public void drawThreeDimensionCircle(Particle particle, Location location, double radius, int point) {

        Double[] result = pointOfThreeDimensionCircle(location.getYaw(),location.getPitch(), radius, point);

        for (int count = 0; count < point; count++){
            location.add(result[count*3],result[count*3 + 1],result[count*3 + 2]);
            drawPoint(particle, location);
            location.subtract(result[count*3],result[count*3 + 1],result[count*3 + 2]);
        }
    }

    //x,y,zの順に格納
    private Double[] pointOfThreeDimensionCircle(float yaw,float pitch, double radius, int point){
        Double[] result = new Double[point *3];

        Location location = new Location(null,0,0,0,yaw,pitch);
        double rYaw = Math.toRadians(location.getYaw());
        Vector v1 = location.getDirection();

        Vector v2 = new Vector(Math.cos(rYaw), 0, Math.sin(rYaw));
        //角速度ω
        double i = 2 * Math.PI / point;

        for (int count = 0; count < point; count++) {

            double angle = i * count;

            result[count*3] = radius * v1.getX() * Math.cos(angle) + radius * v2.getX() * Math.sin(angle);
            result[count*3 + 1] = radius * v1.getY() * Math.cos(angle) + radius * v2.getY() * Math.sin(angle);
            result[count*3 + 2] = radius * v1.getZ() * Math.cos(angle) + radius * v2.getZ() * Math.sin(angle);

        }
        return result;
    }

    @Override
    public void drawTriangle(Particle particle, Location center, double radius, double space, double first) {
        Location location = center.clone();
        location.setYaw((float)first);
        drawPolygon(particle,location,radius,3,space);
    }

    @Override
    public void drawPolygon(Particle particle, Location location, double radius, int count, double space) {
        location.setPitch(0);
        drawThreeDimensionPolygon(particle, location, radius, count, space);
    }

    @Override
    public void drawThreeDimensionPolygon(Particle particle, Location location, double radius, int count, double space){

        if (count == 0) return;

        Double[] result = pointOfThreeDimensionCircle(location.getYaw(),location.getPitch(), radius, count);

        Location location2 = location.clone();
        for (int innerCount = 0;innerCount < count;){
            double x = result[innerCount*3];
            double y = result[innerCount*3 + 1];
            double z = result[innerCount*3 + 2];
            location.add(x,y,z);

            innerCount++;

            double x2;
            double y2;
            double z2;
            if (innerCount == count){
                x2 = result[0];
                y2 = result[1];
                z2 = result[2];
            }else {
                x2 = result[innerCount * 3];
                y2 = result[innerCount * 3 + 1];
                z2 = result[innerCount * 3 + 2];
            }

            location2.add(x2,y2,z2);

            drawLine(particle, location, location2, space);

            location.subtract(x,y,z);
            location2.subtract(x2,y2,z2);
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

    public boolean drawPicture(String name,Location location){
        return drawPicture(name,location,null);
    }

    public boolean drawPicture(String name,Location location,Integer width){
        return drawPicture(name, location, width,0.2f);
    }

    public boolean drawPicture(String name,Location location,Integer width,Float step){
        PictureWriter dp = null;
        for (PictureWriter value: writers){
            if (value.getName().equals(name)){
                if (width == null || value.getWidth() == width){
                    dp = value;
                }
            }
        }
        if (dp == null){
            if(width == null){
                dp = PictureWriter.getInstance(name);
            }else if (width > 0){
                dp = PictureWriter.getInstance(name,width);
            }
        }
        if (dp != null){
            dp.draw(location,step);
            return true;
        }else {
            return false;
        }
    }

}
