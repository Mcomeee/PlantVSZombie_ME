package Plants;

import ReadXML.DataDom;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Bullet.PeaBullet;
import javax.xml.crypto.Data;

public class PeaShooter extends Plant {
  DataDom plantTest = new DataDom();
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

  public PeaShooter(Point point) throws Exception {
    super(point, 71, 71);
    // TODO 自动生成的构造函数存根
    DataDom dataDom = new DataDom();
    this.cost = dataDom.findPlant("PeaShooter").getcost();
    this.HitPoint = dataDom.findPlant("PeaShooter").getHitPoint(); // 生命值
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
