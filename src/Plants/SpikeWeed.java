package Plants;

import java.awt.Point;

import java.awt.image.BufferedImage;
import java.io.File;


import javax.imageio.ImageIO;

public class SpikeWeed extends Plant {

    private static BufferedImage[] imgs;

    static {
        try {
            imgs = new BufferedImage[19];
            for (int i = 0; i < 19; i++) {
                File file = new File("graphics/Plants/SpikeWeed/SpikeWeed/Spikeweed_" + i + ".png");
                imgs[i] = ImageIO.read(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    public SpikeWeed(Point point){
        super("SpikeWeed", point, 71, 71);
    }

    @Override
    public BufferedImage getImage() {
        // TODO 自动生成的方法存根
        return imgs[cnt % 19];
    }
}
