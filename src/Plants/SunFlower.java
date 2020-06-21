package Plants;

import ReadXML.DataDom;
import Sun.Sun;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class SunFlower extends Plant {
  private List<Sun> SunList = new ArrayList<>();
  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[18];
      for (int i = 0; i < 18; i++) {
        File file = new File("graphics/Plants/SunFlower/SunFlower_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public SunFlower(Point point) throws Exception {
    super(point, 73, 74);
    // TODO 自动生成的构造函数存根
    DataDom dataDom=new DataDom();
    this.cost = dataDom.findPlant("SunFlower").getcost();
    this.HitPoint = dataDom.findPlant("SunFlower").getHitPoint();
    this.blood = this.HitPoint;
    BulletList = null;
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    return imgs[index++ % 18];
  }

  @Override
  public void setBullet() {
    // TODO 自动生成的方法存根
  }

  public void setSun() {
    if (this.SunList.size() == 0) {
      int x = point.x;
      int y = point.y;
      this.SunList.add(new Sun(new Point(x + 10, y + 3), y + 20));
    }
  }

  public List<Sun> getSun() {
    return SunList;
  }
}
