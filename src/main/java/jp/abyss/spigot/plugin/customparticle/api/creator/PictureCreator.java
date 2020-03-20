package jp.abyss.spigot.plugin.customparticle.api.creator;

import jp.abyss.spigot.plugin.customparticle.api.entity.particle.Picture;
import org.bukkit.Location;

/**
 * このインターフェイスは {@link Picture}を生成する際に使用します。
 */

public interface PictureCreator {

    /**
     * {@link Picture}を生成します。
     *
     * @param name     画像ファイルの名前 (データフォルダーにある)
     * @param location 描画時の始点
     * @return {@link Picture}
     */
    Picture createPicture(String name, Location location);

    /**
     * {@link Picture}を指定した横幅にリサイズして生成します。
     *
     * @param name     画像ファイルの名前 (データフォルダーにある)
     * @param location 描画時の始点
     * @param width    画像の横幅 (縦幅は自動調整)
     * @return {@link Picture}
     */
    Picture createPicture(String name, Location location, Integer width);

    /**
     * {@link Picture}を指定した横幅、解像度にリサイズして生成します。
     *
     * @param name     画像ファイルの名前 (データフォルダーにある)
     * @param location 描画時の始点
     * @param width    画像の横幅 (縦幅は自動調整)
     * @param step     解像度
     * @return {@link Picture}
     */
    Picture createPicture(String name, Location location, Integer width, Float step);
}
