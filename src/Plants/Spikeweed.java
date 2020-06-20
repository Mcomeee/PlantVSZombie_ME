package Plants;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Bullet.*;
import Bullet.PeaBullet;

public class Spikeweed extends Plant {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[19];
      for (int i = 0; i < 19; i++) {
        File file = new File("graphics/Plants/Spikeweed/Spikeweed/Spikeweed_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public Spikeweed(Point point) {
    super(point, 71, 71);
    // TODO 自动生成的构造函数存根
    this.cost = 100;
    this.HitPoint = 300;
    this.blood = this.HitPoint;
  }

  public void setBullet() {}

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    return imgs[index++ % 19];
  }
}
