package jp.abyss.spigot.plugin.customparticle.api.entity.particle;

import jp.abyss.spigot.plugin.customparticle.api.entity.SpawnEntityAble;
import org.bukkit.Location;

/**
 * このインターフェイスはパーティクルの画像を表します。
 */

public interface Picture extends SpawnEntityAble {

    /**
     * 画像のファイル名を取得します。
     *
     * @return ファイル名
     */
    String getFileName();

    /**
     * 画像の描画始点の座標を取得します。
     *
     * @return 描画始点の座標
     */
    Location getStartingLocation();

    /**
     * パーティクルの横幅を取得します。
     *
     * @return 画像の横幅
     */
    Integer getWidth();

    /**
     * パーティクルの解像度を取得します。
     *
     * @return 画像の解像度
     */
    float getStep();

}
