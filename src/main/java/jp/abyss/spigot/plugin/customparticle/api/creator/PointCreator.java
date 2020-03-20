package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Point;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * このインターフェイスは {@link Point}を生成する際に使用します。
 */

public interface PointCreator {

    /**
     * {@link Point}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   点の座標
     * @return {@link Point}
     */
    Point createPoint(Particle particle, Location center);

}
