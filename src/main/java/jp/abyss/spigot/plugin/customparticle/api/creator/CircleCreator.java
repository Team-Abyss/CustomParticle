package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Circle;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * このインターフェイスは {@link Circle}を生成する際に使用します。
 */
public interface CircleCreator extends ThreeDimensionCircleCreator {

    /**
     * {@link Circle}を生成します。
     *
     * @param particle パーティクルの種類
     * @param center   円の中心座標
     * @param radius   円の半径
     * @param quantity 点の数
     * @return {@link Circle}
     */
    Circle createCircle(Particle particle, Location center, double radius, int quantity);

    /**
     * {@link Circle}を生成します。 点の数は32個になります。
     *
     * @param particle パーティクルの種類
     * @param center   円の中心座標
     * @param radius   円の半径
     * @return {@link Circle}
     */
    Circle createCircle(Particle particle, Location center, double radius);

}
