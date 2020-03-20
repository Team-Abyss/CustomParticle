package jp.abyss.spigot.plugin.customparticle.api.entity;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Point;

import java.util.List;

/**
 * このインターフェイスはパーティクルの点の集合体を表します。
 */

public interface PointAggregation extends SpawnEntityAble {

    /**
     * パーティクルの点の集合を取得します。
     *
     * @return 点の集合
     */
    List<Point> getPoints();

}
