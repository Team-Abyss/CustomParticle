package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Polygon;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * このインターフェイスは {@link Polygon}を生成する際に使用します。
 */

public interface PolygonCreator extends LineCreator {

    /**
     * {@link Polygon}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   中心 (yawとpitchが多角形の傾き)
     * @param radius   中心と頂点の距離
     * @param quantity 頂点の数
     * @param space    隣り合う頂点を結ぶ点の間隔
     * @return {@link Polygon}
     */
    Polygon createPolygon(Particle particle, Location center, double radius, int quantity, double space);
}
