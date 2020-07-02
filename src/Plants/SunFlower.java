package Plants;

import ReadXML.DataDom;
import Sun.Sun;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class SunFlower extends Plant implements Producer {
    private List<Sun> SunList = new ArrayList<>();
    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[18];
            for (int i = 0; i < 18; i++) {
                File file = new File("graphics/Plants/SunFlower/SunFlower_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SunFlower(Point point) {
        super("SunFlower", point, 73, 74);
    }

    @Override
    public BufferedImage getImage() {
        // TODO 自动生成的方法存根
        return imgs[cnt % 18];
    }

    @Override
    public Sun produce() {
        if (cnt % 73 != 0) return null;
        Point point = this.getPoint();
        System.out.println(1);
        return new Sun(
                new Point(point.x + 10, point.y + 3),
                new Point(point.x + 15, point.y + 20)
        );
    }
}
