package jp.abyss.spigot.plugin.customparticle.api.entity.particle;

import jp.abyss.spigot.plugin.customparticle.api.entity.PointAggregation;
import org.bukkit.Location;

/**
 * このインターフェイスはパーティクルの円(平面)を表します。
 */

public interface Circle extends PointAggregation {

    /**
     * 円の半径を取得します。
     *
     * @return 半径
     */
    double getRadius();

    /**
     * 円の中心座標を取得します。
     *
     * @return 中心座標
     */
    Location getCenter();

    /**
     * 円の点の数を取得します。
     *
     * @return 点の数
     */
    int getQuantity();


}
