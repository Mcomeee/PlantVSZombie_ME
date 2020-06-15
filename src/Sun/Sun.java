package Sun;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sun {
	
	private Point sunPoint;		//阳光位置
	private int num;
	private int lastY;		//掉落位置
	private boolean isclick;		//是否被点到
	
	private static BufferedImage images[]=new BufferedImage[22];
	static {
		try {
			for(int i=0;i<22;i++) {
				File file=new File("graphics/Plants/Sun/Sun_"+i+".png");
				images[i]=ImageIO.read(file);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Sun(Point p) {
		this.sunPoint=p;
	}
	
	public Sun(Point p,int y) {
		this.sunPoint=p;
		this.lastY=y;
	}
	
	int index=1;
	public BufferedImage getImage() {
		return images[index++%22];
	}
	
	public void placeSun(Graphics g) {
		g.drawImage(getImage(),sunPoint.x,sunPoint.y,null);
	}
	
	//获取阳光矩形
	public Rectangle getSunRec() {
		return new Rectangle(sunPoint.x,sunPoint.y,78,78);
	}
	
	//阳光回收
	public void recover() {
		if(sunPoint.x>50) {
			sunPoint.x-=70;
			sunPoint.y-=70;
		}
	}
	
	//阳光掉落
	public void move() {
		if(sunPoint.y<lastY) {
			sunPoint.y+=3;
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

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public boolean isIsclick() {
		return isclick;
	}

	public void setIsclick(boolean isclick) {
		this.isclick = isclick;
	}
	
	

}
