package Zombies;

import ReadXML.DataDom;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class NewspaperZombie extends Zombie {
    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[18 + 8 + 11];
            for (int i = 0; i < 18; i++) {
                File file =
                        new File(
                                "graphics/Zombies/NewspaperZombie/NewspaperZombie/NewspaperZombie_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
            for (int i = 0; i < 8; i++) {
                File file =
                        new File(
                                "graphics/Zombies/NewspaperZombie/NewspaperZombieAttack/NewspaperZombieAttack_"
                                        + i
                                        + ".png");
                imgs[i + 18] = ImageIO.read(file);
            }
            for (int i = 0; i < 11; i++) {
                File file =
                        new File(
                                "graphics/Zombies/NewspaperZombie/NewspaperZombieDie/NewspaperZombieDie_"
                                        + i
                                        + ".png");
                imgs[i + 18 + 8] = ImageIO.read(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    public NewspaperZombie(int last) throws Exception {
        super(166, 144, last);
        DataDom dataDom = new DataDom();
        this.attack = dataDom.findZombie("NewspaperZombie").getAttack();
        this.HitPoint = dataDom.findZombie("NewspaperZombie").getHitPoint();
        this.blood = this.HitPoint;
        this.speed = dataDom.findZombie("NewspaperZombie").getSpeed();
    }

    int index = 1;

    public BufferedImage getImage() {

        if (this.isALIVE()) return imgs[index++ % 18];
        else if (this.isATTACK()) return imgs[(index++ % 8) + 18];
        else return imgs[(index++ % 11) + 8 + 18];
    }
}
