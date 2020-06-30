package Plants;

import Bullet.Bullet;
import Bullet.SnowPeaBullet;
import ReadXML.DataDom;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

// 寒冰射手
public class SnowPeaShooter extends Plant implements Shooter {

    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[15];
            for (int i = 0; i < 15; i++) {
                File file = new File("graphics/Plants/SnowPea/SnowPea_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    public SnowPeaShooter(Point point){
        super("SnowPeaShooter", point, 71, 71);
    }

    @Override
    public Bullet shoot() {
        Point point = this.getPoint();
        Point tar = new Point(point.x + 30, point.y + 30);
        return new SnowPeaBullet(tar);
    }

    @Override
    public BufferedImage getImage() {
        // TODO 自动生成的方法存根
        return imgs[cnt % 10];
    }
}
