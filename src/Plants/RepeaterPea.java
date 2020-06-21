package Plants;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Bullet.*;
import Bullet.PeaBullet;
// 双发豌豆
public class RepeaterPea extends Plant {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[15];
      for (int i = 0; i < 15; i++) {
        File file = new File("graphics/Plants/RepeaterPea/RepeaterPea_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public RepeaterPea(Point point) {
    super(point, 71, 71);
    // TODO 自动生成的构造函数存根
    this.cost = 100;
    this.HitPoint = 300;
    this.blood = this.HitPoint;
  }

  public void setBullet() {
    if (this.BulletList.size() < 1) {
      int x = point.x;
      int y = point.y;
      this.BulletList.add(new RepeaBullet(new Point(x + 50, y + 5)));
    }
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    return imgs[index++ % 10];
  }
}
