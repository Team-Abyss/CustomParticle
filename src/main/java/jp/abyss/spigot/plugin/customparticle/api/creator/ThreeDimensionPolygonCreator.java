package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Polygon;
import jp.abyss.spigot.plugin.customparticle.api.entity.particle.ThreeDimensionPolygon;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * このインターフェイスは {@link ThreeDimensionPolygon}を生成する際に使用します。
 */

public interface ThreeDimensionPolygonCreator extends PointCreator {

    /**
     * {@link Polygon}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   中心 (yawとpitchが円の傾き)
     * @param radius   中心と頂点の距離
     * @param quantity 頂点の数
     * @param space    隣り合う頂点を結ぶ点の間隔
     * @return {@link ThreeDimensionPolygon}
     */
    ThreeDimensionPolygon createThreeDimensionPolygon(Particle particle, Location center, double radius, int quantity, double space);
}
