package jp.abyss.spigot.plugin.customparticle.core;

import org.bukkit.Location;

public interface DrawPictureAble{
    boolean drawPicture(String name, Location location);

    boolean drawPicture(String name,Location location,Integer width);
}
