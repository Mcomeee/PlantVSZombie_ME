package Plants;

import ReadXML.DataDom;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Bullet.*;
import Bullet.PeaBullet;

import javax.xml.crypto.Data;

// 双发豌豆
public class RepeaterPea extends Plant implements Shooter {

    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[15];
            for (int i = 0; i < 15; i++) {
                File file = new File("graphics/Plants/RepeaterPea/RepeaterPea_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    public RepeaterPea(Point point) throws Exception {
        super("RepeaterPea", point, 71, 71);
    }


    @Override
    public BufferedImage getImage() {
        // TODO 自动生成的方法存根
        return imgs[cnt % 10];
    }

    @Override
    public Bullet shoot() {
        Point point = this.getPoint();
        Point tar = new Point(point.x + 30, point.y + 30);
        return new PeaBullet(tar);
    }

}
