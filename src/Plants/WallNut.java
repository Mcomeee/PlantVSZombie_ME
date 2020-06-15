package Plants;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class WallNut extends Plant {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[16];
      for (int i = 0; i < 16; i++) {
        File file = new File("graphics/Plants/WallNut/WallNut/WallNut_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public WallNut(Point point) {
    super(point, 65, 73);
    this.cost = 50;
    this.HitPoint = 4000;
    this.blood = this.HitPoint;
    BulletList = null;
  }

  int index = 1;

  public BufferedImage getImage() {
    return imgs[index++ % 16];
  }

  @Override
  public void setBullet() {
    // TODO �Զ����ɵķ������
  }
}
