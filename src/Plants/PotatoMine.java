package Plants;

import ReadXML.DataDom;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.xml.crypto.Data;

/*
 *土豆比较特殊，她要和僵尸有接触的
 * 然后还有特殊的图像处理
 */
public class PotatoMine extends Plant {
  private static BufferedImage[] imgs;

  // 7个图像呈连续
  static {
    try {
      imgs = new BufferedImage[59];
      for (int i = 0; i < 8; i++) {
        File file = new File("graphics/Plants/PotatoMine/PotatoMine/PotatoMine_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }

      for (int i = 8; i < 16; i++) {
        File file =
            new File("graphics/Plants/PotatoMine/PotatoMineExplode/PotatoMineExplode_0.png");
        imgs[i] = ImageIO.read(file);
      }

      for (int i = 16; i < 35; i++) {
        File file = new File("graphics/Plants/CherryBomb/BoomDie2/BoomDie2-" + (i - 16) + ".png");
        imgs[i] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };
  // 构造方法
  /*
  特殊在它可以没有血量，要有消费
  */
  public PotatoMine(Point point) throws Exception {
    super(point, 71, 71);
    DataDom dataDom = new DataDom();
    this.cost = dataDom.findPlant("PotatoMine").getcost();
    this.HitPoint = dataDom.findPlant("PotatoMine").getHitPoint();
    this.blood = this.HitPoint;
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    // TODO 自动生成的方法存根
    if (status == 3) {
      if (index >= 35) {
        return null;
      } else return imgs[index++ % 35];
    } else {
      if (index >= 15) return null;
      else return imgs[index++ % 15];
    }
    /*if(index==46){
      return null;
    }
    return imgs[index++ % 47];*/
  }

  @Override
  // 效果
  public void setBullet() {}
}
