package Zombies;

import ReadXML.DataDom;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class NormalZombie extends Zombie {

    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[22 + 21 + 10];
            for (int i = 0; i < 22; i++) {
                File file = new File("graphics/Zombies/NormalZombie/Zombie/Zombie_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
            for (int i = 0; i < 21; i++) {
                File file =
                        new File("graphics/Zombies/NormalZombie/ZombieAttack/ZombieAttack_" + i + ".png");
                imgs[i + 22] = ImageIO.read(file);
            }
            for (int i = 0; i < 10; i++) {
                File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
                imgs[i + 43] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NormalZombie() {
        super("NormalZombie", 166, 144);
    }

    @Override
    public BufferedImage getImage() {
        if (this.isALIVE()) return imgs[cnt % 22];
        else if (this.isATTACK()) return imgs[(cnt % 21) + 22];
        return imgs[(cnt % 10) + 43];
    }
}
