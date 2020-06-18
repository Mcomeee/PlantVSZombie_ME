package Bullet;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Bullet {
  protected Point Bulletpoint;
  protected static int typeNum;
  protected boolean isHit;
  protected int attack;
  protected int speed;

  public Bullet(Point point) {
    super();
    this.Bulletpoint = point;
  }

  public abstract BufferedImage getImage();

  // ����ͼƬ
  public void placeImage(Graphics g) {
    g.drawImage(getImage(), Bulletpoint.x, Bulletpoint.y, null);
  }


  // ��ȡ�ӵ�����
  public Rectangle getBullteRec() {
    return new Rectangle(Bulletpoint.x+22, Bulletpoint.y, 34, 34);
  }

  // 子弹的移动
  public void move() {
    if (Bulletpoint.x < 1400) {
      Bulletpoint.x += speed;
    }
  }

  public Point getPoint() {
    return Bulletpoint;
  }

  public void setPoint(Point point) {
    this.Bulletpoint = point;
  }

  public int getTypeNum() {
    return typeNum;
  }

  public void setTypeNum(int typeNum) {
    this.typeNum = typeNum;
  }

  public int getSpeed() {
    return this.speed;
  }

  public void setSpeed(int s) {
    this.speed = s;
  }

  public boolean isHit() { return isHit; }

  public void setHit(boolean isHit) {
    this.isHit = isHit;
  }

  public int getAttack() {
    return attack;
  }
}
