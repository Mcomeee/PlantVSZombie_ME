package Plants;

//import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/*
 *妯辨鐐稿脊姣旇緝鐗规畩锛屽ス瑕佸拰鍍靛案鏈夋帴瑙︾殑
 * 鐒跺悗杩樻湁鐗规畩鐨勫浘鍍忓鐞�
 */
public class CherryBomb extends Plant{
    private static BufferedImage[] imgs;

//7涓浘鍍忓憟杩炵画
    static {
        try {
            imgs = new BufferedImage[47];
            for (int i = 0; i < 7; i++) {
                File file = new File("graphics/Plants/CherryBomb/CherryBomb_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }

            for(int i=7;i<16;i++) {
                File file = new File("graphics/Plants/CherryBomb/Boom/Boom.png");
                imgs[i] = ImageIO.read(file);
            }

            for(int i=16;i<35;i++) {
                File file = new File("graphics/Plants/CherryBomb/BoomDie2/BoomDie2-" + (i-16) + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    //鏋勯�犳柟娉�
    /*
    鐗规畩鍦ㄥ畠鍙互娌℃湁琛�閲忥紝瑕佹湁娑堣垂
     */
    public CherryBomb(Point point) {
        super(point, 71, 71);
        this.cost = 150;
        this.HitPoint = 300;
        this.blood = this.HitPoint;
    }

    int index = 1;
    @Override
    public BufferedImage getImage() {
        // TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
        if(index==46){
            return null;
        }
        return imgs[index++ % 47];

    }

    @Override
    //鏁堟灉
    public void setBullet() {

    }
}
