package jp.abyss.spigot.plugin.customparticle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureWriter {

    public static PictureWriter getInstance(String fileName){
        return getInstance(fileName,null);
    }

    public static PictureWriter getInstance(String fileName, Integer width){
        File file = new File(CustomParticle.getPlugin().getDataFolder(), fileName);
        return getInstance(file,width);
    }

    public static PictureWriter getInstance(File file, Integer width){
        try {

            if (width !=null) {
                return new PictureWriter(file,width);
            }else {
                return new PictureWriter(file);
            }
        } catch (IOException e){
            return null;
        }
    }

    public static PictureWriter getInstance(File file){
        return getInstance(file,null);
    }

    private PictureWriter(File file) throws IOException {
        this.file = file;
        BufferedImage image = ImageIO.read(file);
        this.image = image;
        this.width = image.getWidth();
    }
    private PictureWriter(File file, int width) throws IOException {
        this(file);
        setWidth(width);
    }

    private File file;

    private BufferedImage image;

    public String getName(){
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    private int width;

    public int getWidth(){
        return width;
    }

    public void setWidth(int new_w){
        this.width = new_w;
        int w = image.getWidth();
        int h = image.getHeight();
        int new_h = new_w * h / w;
        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance((double) new_w / w, (double) new_h / h), AffineTransformOp.TYPE_BILINEAR);
        BufferedImage newImage = new BufferedImage(new_w, new_h, image.getType());
        op.filter(image, newImage);
        this.image = newImage;
    }

    private float step = 0.2f;

    public void draw(Location location){
        int height = image.getHeight();
        double radius = Math.toRadians(location.getYaw());
        Vector sideVector = new Vector(Math.cos(radius),0,Math.sin(radius)).multiply(step);

        Vector downVector = new  Vector();
        double rPitch = Math.toRadians(location.getPitch());
        downVector.setY(- Math.cos(rPitch));
        double length = - Math.sin(rPitch);
        double rYaw = Math.toRadians(location.getYaw());
        downVector.setX(-length * Math.sin(rYaw));
        downVector.setZ(length * Math.cos(rYaw));
        downVector.multiply(step);

        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < height; y++){
                Color color = new Color(image.getRGB(x,y),true);
                if (color.getAlpha() != 0) {
                    if (color.getRed() == 0) {
                        location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 0.0001, color.getGreen() / 255f, color.getBlue() / 255f, 1);
                    } else {
                        location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1);
                    }
                }
                // location.getWorld().spawnParticle(Particle.REDSTONE,location,10);
                location.add(downVector);
            }
            location.subtract(downVector.clone().multiply(height));
            location.add(sideVector);
        }
    }

}
