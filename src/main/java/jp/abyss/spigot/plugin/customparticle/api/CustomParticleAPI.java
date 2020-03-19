package jp.abyss.spigot.plugin.customparticle.api;

/**
 * このインターフェイスは外部からCustomParticleを参照する際に使用します。
 * CustomParticle.getParticleManager(); から取得できます。
 */

public interface CustomParticleAPI {

    /**
     * {@link ParticleManager}を取得します。
     *
     * @return CustomParticleの保有する {@link ParticleManager}
     */
    ParticleManager getParticleManager();

}
