package Zombies;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Bullet.Bullet;

// 僵尸的抽象父类
/** @author zhizh */
public abstract class Zombie {
  // 对象的状态
  public static final int ALIVE = 0;
  public static final int ATTACK = 1;
  public static final int DEAD = 2;
  protected int status = ALIVE;

  public void setStatus(int s) {
    status = s;
  }

  public boolean isALIVE() {
    return status == ALIVE;
  }

  public boolean isATTACK() {
    return status == ATTACK;
  }

  public boolean isDEAD() {
    return status == DEAD;
  }

  protected int attack; // 攻击能力
  protected Point point = new Point(0, 0); // 坐标
  protected int width; // 宽度
  protected int length; // 长度
  protected int HitPoint; // 生命值
  protected int blood; // 血量
  protected int speed; // 速度
  protected int DeadTime;
  public int frameNum;

  public Zombie(int width, int length) {
    this.width = width;
    this.length = length;
    Random rand = new Random();
    point.x = 1300;
    point.y = rand.nextInt(600 - length);
  }

  // 获取图片
  public abstract BufferedImage getImage();

  // 放置图片
  public void placeImage(Graphics g) {
    g.drawImage(getImage(), point.x, point.y, null);
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

  public int getAttack() {
    return this.attack;
  }

  public int getSpeed() {
    return this.speed;
  }

  public int getDeadTime(){return DeadTime;}

  public void setDeadTime(int time){
    this.DeadTime=time;
  }
  // 被子弹攻击
  public void isAttacked(Bullet b) {
    this.blood = this.blood - b.getAttack();
    b.setHit(true);
  }

  // 获取僵尸的矩形
  public Rectangle getZombieRec() {
    return new Rectangle(point.x+60, point.y, 84, 144);
  }

  // 僵尸行走
  public void move() {
    if (status != ATTACK) {
      point.x -= speed;
    }
  }

  /* 判断僵尸是否活着
  public boolean isAlive() {
    return status == ALIVE;
  }

  /* 判断僵尸是否挂了
  public boolean isDead() {
    return status == DEAD;
  }

  // 击中后失血
  public void loseBlood() {
    HitPoint--;
  }*/
}
