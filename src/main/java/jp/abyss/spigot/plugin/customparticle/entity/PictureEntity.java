package jp.abyss.spigot.plugin.customparticle.entity;

import jp.abyss.spigot.plugin.customparticle.PictureWriter;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Picture;
import org.bukkit.Location;


public class PictureEntity implements Picture {

    private String fileName;
    private Location startingLocation;
    private Integer width;
    private float step;


    public PictureEntity(String fileName, Location startingLocation, Integer width, float step) {
        this.fileName = fileName;
        this.startingLocation = startingLocation;
        this.width = width;
        this.step = step;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public Location getStartingLocation() {
        return startingLocation.clone();
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public float getStep() {
        return step;
    }

    @Override
    public boolean spawn() {
        return PictureWriter.getPictureWrite(this).draw(this);
    }

}
