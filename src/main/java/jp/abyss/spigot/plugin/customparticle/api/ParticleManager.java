package jp.abyss.spigot.plugin.customparticle.api;

import jp.abyss.spigot.plugin.customparticle.api.creator.*;

/**
 * このインターフェイスは外部からカスタムパーティクルエンティティを作成する際に使用します。
 */

public interface ParticleManager extends ThreeDimensionCircleCreator, TriangleCreator, PointCreator, PolygonCreator, ThreeDimensionPolygonCreator, LineCreator, CircleCreator, PictureCreator {
}
