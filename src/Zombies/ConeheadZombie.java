package Zombies;

import ReadXML.DataDom;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ConeheadZombie extends Zombie {
  private static BufferedImage[] imgs;

  static {
    try {
      imgs = new BufferedImage[21 + 11 + 10];
      for (int i = 0; i < 21; i++) {
        File file =
            new File("graphics/Zombies/ConeheadZombie/ConeheadZombie/ConeheadZombie_" + i + ".png");
        imgs[i] = ImageIO.read(file);
      }
      for (int i = 0; i < 11; i++) {
        File file =
            new File(
                "graphics/Zombies/ConeheadZombie/ConeheadZombieAttack/ConeheadZombieAttack_"
                    + i
                    + ".png");
        imgs[i + 21] = ImageIO.read(file);
      }
      for (int i = 0; i < 10; i++) {
        File file = new File("graphics/Zombies/NormalZombie/ZombieDie/ZombieDie_" + i + ".png");
        imgs[i + 21 + 11] = ImageIO.read(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  public ConeheadZombie(int last) throws Exception {
    super(166, 144, last);
    DataDom dataDom = new DataDom();
    this.attack = dataDom.findZombie("ConeheadZombie").getAttack();
    this.HitPoint = dataDom.findZombie("ConeheadZombie").getHitPoint();
    this.blood = this.HitPoint;
    this.speed = dataDom.findZombie("ConeheadZombie").getSpeed();
  }

  int index = 1;

  public BufferedImage getImage() {

    if (this.isALIVE()) return imgs[index++ % 21];
    else if (this.isATTACK()) return imgs[(index++ % 11) + 21];
    else return imgs[(index++ % 10) + 32];
  }
}
