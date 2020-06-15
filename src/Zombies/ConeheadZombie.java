package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ConeheadZombie extends Zombie{
  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[20];
      for (int i = 0; i < 20; i++) {
        File file = new File("graphics/Zombies/ConeheadZombie/ConeheadZombie/ConeheadZombie_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public ConeheadZombie() {
    super(166, 144);
    this.frameNum = 12;
    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 2;
  }

  int index = 1;

  public BufferedImage getImage() {
    return imgs[index++ % 20];
  }
}
