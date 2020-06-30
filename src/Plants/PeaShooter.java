package Plants;

import Bullet.Bullet;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PeaShooter extends Plant implements Shooter {

    private static BufferedImage[] imgs;
    private int cnt;

    static {
        try {
            imgs = new BufferedImage[10];
            for (int i = 0; i < 10; i++) {
                File file = new File("graphics/Plants/Peashooter/Peashooter_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    public PeaShooter(Point point){
        super("PeaShooter", point, 71, 71);
    }

    @Override
    public BufferedImage getImage() {
        return imgs[cnt++ % 10];
    }

    @Override
    public Bullet shoot() {
        return null;
    }
}
