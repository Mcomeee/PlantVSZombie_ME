package Zombies;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FootballZombie extends Zombie{
  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[5];
      for (int i = 0; i < 5; i++) {
        File file = new File("graphics/Zombies/FootballZombie/FootballZombie/FootballZombie_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public FootballZombie() {
    super(166, 144);
    this.frameNum = 12;
    this.attack = 100;
    this.HitPoint = 270;
    this.blood = this.HitPoint;
    this.speed = 2;
  }

  int index = 1;

  public BufferedImage getImage() {
    return imgs[index++ % 5];
  }
}
