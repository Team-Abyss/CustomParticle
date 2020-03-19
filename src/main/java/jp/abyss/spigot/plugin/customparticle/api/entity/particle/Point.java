package jp.abyss.spigot.plugin.customparticle.api.entity.particle;

import jp.abyss.spigot.plugin.customparticle.api.entity.ParticleHolder;
import org.bukkit.Location;

/**
 * このインターフェイスはパーティクルの点を表します。
 */

public interface Point extends ParticleHolder {

    /**
     * 点の座標を取得します
     *
     * @return 点の座標
     */
    Location getLocation();

}
