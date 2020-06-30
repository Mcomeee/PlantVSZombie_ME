package Bullet;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Bullet {
    private Point point;
    boolean isHit;
    int attack;
    int speed;

    public Bullet(Point point) {
        super();
        this.point = point;
    }

    public abstract BufferedImage getImage();

    // 放置图片
    public void placeImage(Graphics g) {
        g.drawImage(getImage(), point.x, point.y, null);
    }

    // 获取子弹矩形
    public Rectangle getBulletRec() {
        return new Rectangle(point.x + 22, point.y, 34, 34);
    }

    // 子弹的移动
    public void move() {
        if (point.x < 1400) {
            point.x += speed;
        }
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public int getAttack() {
        return attack;
    }
}
