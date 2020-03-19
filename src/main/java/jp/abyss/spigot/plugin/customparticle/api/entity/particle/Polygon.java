package jp.abyss.spigot.plugin.customparticle.api.entity.particle;

/**
 * このインターフェイスはパーティクルの多角形(平面)を表します。
 */

public interface Polygon extends Circle {

    /**
     * 隣り合う頂点を結ぶ点の間隔を取得します。
     *
     * @return 点の間隔
     */
    double getSpace();

}
