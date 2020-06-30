package Plants;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Bullet.Bullet;
import ReadXML.DataDom;
import Zombies.Zombie;

// ֲ植物的抽象父类
public abstract class Plant {

    private int blood; // 血量
    private Point point; // 坐标
    private Rectangle rec;
    private int cost; // 阳光花费
    private boolean isAlive; // 是否仍然存活
    int cnt;

    private static DataDom dataDom = new DataDom();

    public Plant(String name, Point point, int width, int height) {
        this.point = point;
        this.rec = new Rectangle(point.x, point.y, width, height);
        this.isAlive = true;
        this.cost = dataDom.findPlant(name).get("cost");
        this.blood = dataDom.findPlant(name).get("blood");
    }

    // 获取图片
    public abstract BufferedImage getImage();

    // 放置图片
    public void placeImage(Graphics g) {
        g.drawImage(getImage(), point.x, point.y, null);
    }

    public void action(){
        if (this.getBlood() <= 0)
            this.isAlive = false;
        ++ cnt;
    };

    // 植物被僵尸攻击
    public void isAttacked(Zombie z) {
        blood = blood - z.getAttack();
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public Point getPoint() {
        return point;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getBlood() {
        return this.blood;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
