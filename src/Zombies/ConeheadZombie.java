package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ConeheadZombie extends Zombie{
  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[20+11+10];
      for (int i = 0; i < 20; i++) {
        File file = new File("graphics/Zombies/ConeheadZombie/ConeheadZombie/ConeheadZombie_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
      for (int i = 0; i < 11; i++) {
        File file = new File("graphics/Zombies/ConheadZombie/ConheadZombieAttack/ConheadZombieAttack_" + i + ".png");
        imgs[i+20] = ImageIO.read(file);
      }
      for (int i = 0; i < 10; i++) {
        File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
        imgs[i+20+11] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public ConeheadZombie() {
    super(166, 144);

    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 2;
  }

  int index = 1;

  public BufferedImage getImage() {

    if (this.isALIVE())return imgs[index++ % 20];
    else if (this.isATTACK())return imgs[(index++%11)+20];
    else return imgs[(index++%10)+31];
  }
}
