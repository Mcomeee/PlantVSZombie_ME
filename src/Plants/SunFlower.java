package Plants;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SunFlower extends Plant {

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

  public SunFlower(Point point) {
    super(point, 73, 74);
    // TODO 自动生成的构造函数存根
    this.cost = 50;
    this.HitPoint = 300;
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
}
