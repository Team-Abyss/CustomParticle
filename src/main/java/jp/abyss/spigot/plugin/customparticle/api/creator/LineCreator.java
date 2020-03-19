package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Line;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * このインターフェイスは {@link Line}を生成する際に使用します。
 */

public interface LineCreator extends PointCreator {

    /**
     * {@link Line} を生成します。
     *
     * @param particle パーティクルの種類
     * @param from     線の起点
     * @param to       線の終点
     * @param space    点と点の間隔
     * @return {@link Line}
     */
    Line createLine(Particle particle, Location from, Location to, double space);

}
