package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FootballZombie extends Zombie {
  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[5 + 6+10];
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
        File file =
            new File(
                "graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_"
                    + i
                    + ".png");
        imgs[i + 5+6] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public FootballZombie(int last) {
    super(166, 144,last);

    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 2;
  }

  int index = 1;

  public BufferedImage getImage() {
    if (this.isALIVE()) return imgs[index++ % 5];
    else if (this.isATTACK()) return imgs[(index++ % 6) + 5];
    else return imgs[(index++%10)+5+6];
  }
}
