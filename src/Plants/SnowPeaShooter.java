package Plants;

import Bullet.SnowPeaBullet;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
// 寒冰射手
public class SnowPeaShooter extends Plant {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[15];
      for (int i = 0; i < 15; i++) {
        File file = new File("graphics/Plants/SnowPea/SnowPea_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public SnowPeaShooter(Point point) {
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
      this.BulletList.add(new SnowPeaBullet(new Point(x + 50, y + 5)));
    }
  }

  int index = 1;

  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    return imgs[index++ % 10];
  }
}
