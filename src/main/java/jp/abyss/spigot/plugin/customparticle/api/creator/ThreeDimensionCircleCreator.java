package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.ThreeDimensionCircle;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * 　このインターフェイスは {@link ThreeDimensionCircle}を生成する際に使用します。
 */

public interface ThreeDimensionCircleCreator extends PointCreator {

    /**
     * {@link ThreeDimensionCircle}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   中心
     * @param radius   半径
     * @param quantity 点の数
     * @return {@link ThreeDimensionCircle}
     */
    ThreeDimensionCircle createThreeDimensionCircle(Particle particle, Location center, double radius, int quantity);
}
