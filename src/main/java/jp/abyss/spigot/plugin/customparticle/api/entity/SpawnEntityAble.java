package jp.abyss.spigot.plugin.customparticle.api.entity;

/**
 * このインターフェイスはスポーンできるエンティティを表します。
 */

public interface SpawnEntityAble {

    /**
     * 自身をスポーンさせる。
     *
     * @return スポーンに成功した場合 true 何らかの例外がありスポーンできない場合はfalse
     */
    boolean spawn();
}
