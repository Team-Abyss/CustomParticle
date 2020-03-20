package jp.abyss.spigot.plugin.customparticle.api.entity;

import org.bukkit.Particle;

/**
 * このインターフェイスはパーティクルを所有するエンティティを表します。
 */

public interface ParticleHolder extends SpawnEntityAble {

    /**
     * 点のパーティクルを取得します
     *
     * @return 点のパーティクル
     */
    Particle getParticle();

}
