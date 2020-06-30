package Plants;

import ReadXML.DataDom;
import Zombies.Zombie;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/*
 *樱桃炸弹比较特殊，她要和僵尸有接触的
 * 然后还有特殊的图像处理
 */
public class CherryBomb extends Plant implements Bomb{
    private static BufferedImage[] imgs;
    private boolean exploding;

    // 7个图像呈连续
    static {
        try {
            imgs = new BufferedImage[47];
            for (int i = 0; i < 7; i++) {
                File file = new File("graphics/Plants/CherryBomb/CherryBomb_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }

            for (int i = 7; i < 16; i++) {
                File file = new File("graphics/Plants/CherryBomb/Boom/Boom.png");
                imgs[i] = ImageIO.read(file);
            }

            for (int i = 16; i < 35; i++) {
                File file = new File("graphics/Plants/CherryBomb/BoomDie2/BoomDie2-" + (i - 16) + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CherryBomb(Point point) {
        super("CherryBomb", point, 71, 71);
    }

    @Override
    public BufferedImage getImage() {
        if (!exploding){
            cnt /= 15;
            return imgs[cnt];
        }
        return imgs[(cnt - 15) % 20 + 15];
    }

    @Override
    public void action() {
        super.action();
        if (!exploding) cnt %= 15;
        if (cnt > 34) this.setAlive(false);
    }

    @Override
    public void judgeToBoom(List<Zombie> zombies) {
        if (exploding) return;
        for (Zombie zombie : zombies){
            if (this.getRec().intersects(zombie.getZombieRec())){
                zombie.setStatus(Zombie.DEAD);
                this.boom();
            }
        }
    }

    @Override
    public void boom(){
        exploding = true;
    }

}
