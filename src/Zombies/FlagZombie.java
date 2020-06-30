package Zombies;

import ReadXML.DataDom;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FlagZombie extends Zombie {

    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[12 + 10 + 10];
            for (int i = 0; i < 12; i++) {
                File file = new File("graphics/Zombies/FlagZombie/FlagZombie/FlagZombie_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
            for (int i = 0; i < 10; i++) {
                File file =
                        new File("graphics/Zombies/FlagZombie/FlagZombieAttack/FlagZombieAttack_" + i + ".png");
                imgs[i + 12] = ImageIO.read(file);
            }
            for (int i = 0; i < 10; i++) {
                File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
                imgs[i + 12 + 10] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FlagZombie() {
        super("FlagZombie", 166, 144);
    }

    @Override
    public BufferedImage getImage() {
        // TODO 自动生成的方法存根
        if (this.isALIVE()) return imgs[cnt % 12];
        else if (this.isATTACK()) return imgs[(cnt % 10) + 12];
        return imgs[(cnt % 10) + 22];
    }
}
