package Plants;

import ReadXML.DataDom;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class WallNut extends Plant {

  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[16 + 11 + 15];
      for (int i = 0; i < 16; i++) {
        File file = new File("graphics/Plants/WallNut/WallNut/WallNut_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
      for (int i = 0; i < 11; i++) {
        File file =
            new File("graphics/Plants/WallNut/WallNut_cracked1/WallNut_cracked1_" + i + ".png");
        imgs[i + 16] = ImageIO.read(file);
      }
      for (int i = 0; i < 15; i++) {
        File file =
            new File("graphics/Plants/WallNut/WallNut_cracked2/WallNut_cracked2_" + i + ".png");
        imgs[i + 27] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public WallNut(Point point) throws Exception {
    super(point, 65, 73);
    DataDom dataDom=new DataDom();
    this.cost = dataDom.findPlant("WallNut").getcost();
    this.HitPoint = dataDom.findPlant("WallNut").getHitPoint();
    this.blood = this.HitPoint;
    BulletList = null;
  }

  int index = 1;

  public BufferedImage getImage() {
    if (this.blood > 3000) return imgs[index++ % 16];
    else if (this.blood > 1500) return imgs[(index++ % 11) + 16];
    else return imgs[(index++ % 15) + 27];
  }

  @Override
  public void setBullet() {
    // TODO �Զ����ɵķ������
  }
}