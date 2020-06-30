package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FootballZombie extends Zombie {
    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[5 + 6 + 10];
            for (int i = 0; i < 5; i++) {
                File file =
                        new File("graphics/Zombies/FootballZombie/FootballZombie/FootballZombie_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
            for (int i = 0; i < 6; i++) {
                File file =
                        new File(
                                "graphics/Zombies/FootballZombie/FootballZombieAttack/FootballZombieAttack_"
                                        + i
                                        + ".png");
                imgs[i + 5] = ImageIO.read(file);
            }
            for (int i = 0; i < 10; i++) {
                File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
                imgs[i + 5 + 6] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FootballZombie(int last) throws Exception {
        super("FootballZombie",166, 144);
    }

    public BufferedImage getImage() {
        if (this.isALIVE()) return imgs[cnt % 5];
        else if (this.isATTACK()) return imgs[(cnt % 6) + 5];
        return imgs[(cnt % 10) + 11];
    }
}
