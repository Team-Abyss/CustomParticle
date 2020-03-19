package jp.abyss.spigot.plugin.customparticle.api.entity.particle;

import jp.abyss.spigot.plugin.customparticle.api.entity.PointAggregation;

/**
 * このインターフェイスはパーティクルの線を表します。
 */

public interface Line extends PointAggregation {

    /**
     * 線の始点を取得します。
     *
     * @return 始点
     */
    Point getFrom();

    /**
     * 線の終点を取得します。
     *
     * @return 終点
     */
    Point getTo();

    /**
     * 線を描画する点の間隔を取得します。
     *
     * @return 点の間隔
     */
    double getSpace();

}
