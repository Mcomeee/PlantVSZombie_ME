package Plants;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Bullet.Bullet;
import Zombies.Zombie;

// ֲ植物的抽象父类
public abstract class Plant {
  // 对象的状态
  public static final int LIFE = 0;
  public static final int ATTACK = 1;
  public static final int DEAD = 2;
  protected int status = LIFE;

  public void setStatus(int s) {
    status = s;
  }

  protected List<Bullet> BulletList = new ArrayList<>();
  // protected boolean ate;
  protected int HitPoint; // 生命值
  protected int blood; // 血量
  protected Point point; // 坐标
  protected int width; // 宽度
  protected int length; // 长度
  protected int cost; // 阳光花费

  public Plant(Point point, int width, int length) {
    this.point = point;
    this.width = width;
    this.length = length;
  }

  // 获取图片
  public abstract BufferedImage getImage();

  // 放置图片
  public void placeImage(Graphics g) {
    g.drawImage(getImage(), point.x, point.y, null);
  }

  public abstract void setBullet();

  // 获取植物矩形
  public Rectangle getPlantRec() {
    return new Rectangle(point.x, point.y, width, length);
  }

  public Point getPoint() {
    return point;
  }

  public int getWidth() {
    return this.width;
  }

  public int getLength() {
    return this.length;
  }

  public int getBlood() {
    return this.blood;
  }

  public int getcost() {
    return this.cost;
  }

  public List<Bullet> getBulletList() {
    return this.BulletList;
  }

  // 植物被僵尸攻击
  public void isAttacked(Zombie z) {
    blood = blood - z.getAttack();
  }
}
