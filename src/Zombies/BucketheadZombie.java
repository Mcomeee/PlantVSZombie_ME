package Zombies;

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
  };

  public BucketheadZombie(int last) {
    super(166, 144, last);

    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 2;
  }

  int index = 1;

  public BufferedImage getImage() {

    if (this.isALIVE()) return imgs[index++ % 15];
    else if (this.isATTACK()) return imgs[(index++ % 11) + 15];
    else return imgs[(index++ % 10) + 15 + 11];
  }
}
