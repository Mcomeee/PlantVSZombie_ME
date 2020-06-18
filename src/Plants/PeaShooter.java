package Plants;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Bullet.Bullet;
import Bullet.PeaBullet;

public class PeaShooter extends Plant {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[10];
      for (int i = 0; i < 10; i++) {
        File file = new File("graphics/Plants/Peashooter/Peashooter_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public PeaShooter(Point point) {
    super(point, 71, 71);
    // TODO 自动生成的构造函数存根
    this.cost = 100;
    this.HitPoint = 300;         // 生命值
    this.blood = this.HitPoint;
  }

  public void setBullet() {
    if (this.BulletList.size() < 1) {
      int x = point.x;
      int y = point.y;
      this.BulletList.add(new PeaBullet(new Point(x + 50, y + 5)));
    }
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    return imgs[index++ % 10];
  }
}
