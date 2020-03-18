package jp.abyss.spigot.plugin.customparticle.api.writer;

import org.bukkit.Location;

public interface PictureWriter {
    boolean drawPicture(String name, Location location);

    boolean drawPicture(String name,Location location,Integer width);

    boolean drawPicture(String name,Location location,Integer width,Float step);
}
