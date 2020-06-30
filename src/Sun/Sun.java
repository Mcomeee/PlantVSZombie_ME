package Sun;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sun {

    private Point sunPoint; // 当前位置
    private int num;
    private Point tarPoint; // 目标位置
    private boolean isClicked; // 是否被点到
    private int cnt = 1; // 计数器

    private static BufferedImage images[] = new BufferedImage[22];

    static {
        try {
            for (int i = 0; i < 22; i++) {
                File file = new File("graphics/Plants/Sun/Sun_" + i + ".png");
                images[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sun(Point sunPoint, Point tarPoint) {
        this.sunPoint = sunPoint;
        this.tarPoint = tarPoint;
    }

    public BufferedImage getImage() {
        return images[cnt++ % 22];
    }

    public void placeSun(Graphics g) {
        g.drawImage(getImage(), sunPoint.x, sunPoint.y, null);
    }


    // 获取阳光矩形
    public Rectangle getSunRec() {
        return new Rectangle(sunPoint.x, sunPoint.y, 78, 78);
    }

    // 阳光回收
    public void recover() {
        this.tarPoint.x = 50;
        this.tarPoint.y = 50;
    }

    // 阳光掉落
    public void move() {
        int dx = tarPoint.x - sunPoint.x;
        int dy = tarPoint.y - sunPoint.y;
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        if (absDx > 3) {
            sunPoint.x += dx / absDx * 3;
        }
        if (absDy > 3) {
            sunPoint.y += dy / absDy * 3;
        }
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public Point getSunPoint() {
        return sunPoint;
    }

    public void setSunPoint(Point sunPoint) {
        this.sunPoint = sunPoint;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        this.isClicked = clicked;
    }
}