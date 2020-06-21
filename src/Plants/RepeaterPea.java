package Plants;

import ReadXML.DataDom;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Bullet.*;
import Bullet.PeaBullet;
import javax.xml.crypto.Data;

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

  public RepeaterPea(Point point) throws Exception {
    super(point, 71, 71);
    // TODO 自动生成的构造函数存根
    DataDom dataDom = new DataDom();
    this.cost = dataDom.findPlant("RepeaterPea").getcost();
    this.HitPoint = dataDom.findPlant("RepeaterPea").getHitPoint();
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
