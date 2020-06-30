package Zombies;

import ReadXML.DataDom;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BucketheadZombie extends Zombie {
    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[15 + 11 + 10];
            for (int i = 0; i < 15; i++) {
                File file =
                        new File(
                                "graphics/Zombies/BucketheadZombie/BucketheadZombie/BucketheadZombie_"
                                        + i
                                        + ".png");
                imgs[i] = ImageIO.read(file);
            }
            for (int i = 0; i < 11; i++) {
                File file =
                        new File(
                                "graphics/Zombies/BucketheadZombie/BucketheadZombieAttack/BucketheadZombieAttack_"
                                        + i
                                        + ".png");
                imgs[i + 15] = ImageIO.read(file);
            }
            for (int i = 0; i < 10; i++) {
                File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
                imgs[i + 11 + 15] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BucketheadZombie(int last) throws Exception {
        super("BucketheadZombie", 166, 144);
    }

    public BufferedImage getImage() {
        if (this.isALIVE()) return imgs[cnt % 15];
        else if (this.isATTACK()) return imgs[(cnt % 11) + 15];
        return imgs[(cnt % 10) + 15 + 11];
    }
}
