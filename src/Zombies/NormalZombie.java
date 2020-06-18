package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class NormalZombie extends Zombie {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[22+21+10];
      for (int i = 0; i < 22; i++) {
        File file = new File("graphics/Zombies/NormalZombie/Zombie/Zombie_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
      for (int i = 0; i < 21; i++) {
        File file = new File("graphics/Zombies/NormalZombie/ZombieAttack/ZombieAttack_" + i + ".png");
        imgs[i+22] = ImageIO.read(file);
      }
      for (int i = 0; i < 10; i++) {
        File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
        imgs[i+43] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public NormalZombie(int last) {
    super(166, 144,last);
    // TODO 自动生成的构造函数存根

    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 1;
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    if (this.isALIVE())return imgs[index++ % 22];
    else if (this.isATTACK()) return imgs[(index++%21)+22];
    else return imgs[(index++%10)+43];
  }
}
