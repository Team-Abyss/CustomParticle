package jp.abyss.spigot.plugin.customparticle;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Picture;
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
import java.util.ArrayList;
import java.util.List;

public class PictureWriter {
    private static List<PictureWriter> writers = new ArrayList<>();
    private File file;
    private BufferedImage image;
    private int width;

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

    public static PictureWriter getInstance(String fileName) {
        return getInstance(fileName, null);
    }

    public static PictureWriter getInstance(String fileName, Integer width) {
        File file = new File(CustomParticle.getPlugin().getDataFolder(), fileName);
        return getInstance(file, width);
    }

    public static PictureWriter getInstance(File file, Integer width) {
        try {
            if (width != null) {
                return new PictureWriter(file, width);
            } else {
                return new PictureWriter(file);
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static PictureWriter getInstance(File file) {
        return getInstance(file, null);
    }

    public static PictureWriter getPictureWrite(Picture picture) {
        PictureWriter dp = null;
        for (PictureWriter value : writers) {
            if (value.getName().equals(picture.getFileName())) {
                if (picture.getWidth() == null || value.getWidth() == picture.getWidth()) {
                    dp = value;
                }
            }
        }
        if (dp == null) {
            if (picture.getWidth() == null) {
                dp = PictureWriter.getInstance(picture.getFileName());
            } else if (picture.getWidth() > 0) {
                dp = PictureWriter.getInstance(picture.getFileName(), picture.getWidth());
            }
            writers.add(dp);
        }
        return dp;
    }

    public String getName() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int new_w) {
        this.width = new_w;
        int w = image.getWidth();
        int h = image.getHeight();
        int new_h = new_w * h / w;
        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance((double) new_w / w, (double) new_h / h), AffineTransformOp.TYPE_BILINEAR);
        BufferedImage newImage = new BufferedImage(new_w, new_h, image.getType());
        op.filter(image, newImage);
        this.image = newImage;
    }

    public boolean draw(Picture picture) {
        if (!file.exists()) return false;
        Location location = picture.getStartingLocation();
        float step = picture.getStep();

        int height = image.getHeight();
        double radius = Math.toRadians(location.getYaw());
        Vector sideVector = new Vector(Math.cos(radius), 0, Math.sin(radius)).multiply(step);

        Vector downVector = new Vector();
        double rPitch = Math.toRadians(location.getPitch());
        downVector.setY(-Math.cos(rPitch));
        double length = -Math.sin(rPitch);
        double rYaw = Math.toRadians(location.getYaw());
        downVector.setX(-length * Math.sin(rYaw));
        downVector.setZ(length * Math.cos(rYaw));
        downVector.multiply(step);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y), true);
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
        return true;
    }

}
