package Bullet;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PeaBullet extends Bullet{
	
	private static BufferedImage imgs;
	
	static {
		try {
			imgs=ImageIO.read(new File("graphics/Bullets/PeaNormal/PeaNormal_0.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public PeaBullet(Point point) {
		super(point);
		this.attack=100;
		this.speed=10;
		this.isHit=false;
	}
	
	public BufferedImage getImage() {
		return imgs;
	}
	
}