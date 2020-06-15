package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class NormalZombie extends Zombie {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[22];
      for (int i = 0; i < 22; i++) {
        File file = new File("graphics/Zombies/NormalZombie/Zombie/Zombie_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public NormalZombie() {
    super(166, 144);
    // TODO 自动生成的构造函数存根
    this.frameNum = 22;
    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 1;
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    return imgs[index++ % 22];
  }
}
