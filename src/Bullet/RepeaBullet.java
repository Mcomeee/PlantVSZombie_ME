package Bullet;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class RepeaBullet extends Bullet {

  private static BufferedImage imgs;

  static {
    try {
      imgs = ImageIO.read(new File("graphics/Bullets/PeaNormal/TwoPeaNormal_1.png"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public RepeaBullet(Point point) {
    super(point);
    this.attack = 40;
    this.speed = 10;
    this.isHit = false;
  }

  public BufferedImage getImage() {
    return imgs;
  }
}
