package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Triangle;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * このインターフェイスは {@link Triangle}を生成する際に使用します。
 */

public interface TriangleCreator extends PolygonCreator {

    /**
     * {@link Triangle}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   中心
     * @param radius   　中心と頂点の距離
     * @param space    隣り合う頂点を結ぶ点の間隔
     * @param first    回転 (yaw)
     * @return {@link Triangle}
     */
    Triangle createTriangle(Particle particle, Location center, double radius, double space, double first);

    /**
     * {@link Triangle}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   中心
     * @param radius   　中心と頂点の距離
     * @param space    隣り合う頂点を結ぶ点の間隔
     * @return {@link Triangle}
     */
    Triangle createTriangle(Particle particle, Location center, double radius, double space);
}
