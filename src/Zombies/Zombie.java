package Zombies;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Bullet.Bullet;
import ReadXML.DataDom;

// 僵尸的抽象父类

/**
 * @author zhizh
 */
public abstract class Zombie {
    // 对象的状态
    public static final int ALIVE = 0;
    public static final int ATTACK = 1;
    public static final int DEAD = 2;

    private int status = ALIVE;
    private int attack; // 攻击能力
    private Point point; // 坐标
    private int blood; // 血量
    private int speed; // 速度
    private int DeadTime;
    private Rectangle rec;

    private static DataDom dataDom = new DataDom();

    int cnt;

    public Zombie(String name, int width, int height) {

        this.attack = dataDom.findZombie(name).get("attack");
        this.blood = dataDom.findZombie(name).get("blood");
        this.speed = dataDom.findZombie(name).get("speed");

        int row = new Random().nextInt(5);
        // 存疑
        point = new Point(1300, row * 100 + 100);
        rec = new Rectangle(point.x, point.y, width, height);
    }

    // 获取图片
    public abstract BufferedImage getImage();

    public void action(){
        ++cnt;
        if (this.blood < 0)
            this.status = DEAD;
    }

    // 放置图片
    public void placeImage(Graphics g) {
        g.drawImage(getImage(), point.x, point.y, null);
    }

    // 被子弹攻击
    public void isAttacked(Bullet bullet) {
        this.blood = this.blood - bullet.getAttack();
        bullet.setHit(true);
        if (this.blood < 0) this.setStatus(DEAD);
    }

    // 被地刺攻击
    public void loseBlood() {
        this.blood = this.blood - 100;
        if (this.blood < 0) this.setStatus(DEAD);
    }

    // 获取僵尸的矩形
    public Rectangle getZombieRec() {
        return rec;
    }

    // 僵尸行走
    public void move() {
        if (status != ATTACK && cnt % 2 == 0) {
            point.x -= speed;
            rec.x = point.x;
        }
    }

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

    public Point getPoint() {
        return point;
    }

    public int getBlood() {
        return this.blood;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getStatus() {
        return status;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDeadTime() {
        return DeadTime;
    }

    public void setDeadTime(int time) {
        this.DeadTime = time;
    }

}
