package Plants;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import Bullet.Bullet;
import Zombies.Zombie;

//ֲ��ĳ�����
public abstract class  Plant{
	// �����״̬
	public static final int LIFE = 0;
	public static final int ATTACK = 1;
	public static final int DEAD = 2;
	protected int status = LIFE;
	
	public void setStatus(int s) {
		status=s;
	}

	protected List<Bullet> BulletList=new ArrayList<>();
	//protected boolean ate;
	protected int HitPoint;		//����ֵ
	protected int blood;		//Ѫ��
	protected Point point;		//����
	protected int width;		//���
	protected int length;		//����
	protected int cost;			//���⻨��
	
	public Plant(Point point,int width,int length) {
		this.point=point;
		this.width=width;
		this.length=length;
	}
	
	//��ȡͼƬ
	public abstract BufferedImage getImage();
	
	//����ͼƬ
	public void placeImage(Graphics g) {
		g.drawImage(getImage(),point.x,point.y,null);
	}
	
	public abstract void setBullet();
	
	//��ȡֲ�����
	public Rectangle getPlantRec() {
		return new Rectangle(point.x,point.y,width,length);
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
	
	public int getcost() {
		return this.cost;
	}
	
	public List<Bullet> getBulletList(){
		return this.BulletList;
	}
	
	//ֲ�ﱻ��ʬ����
	public void isAttacked(Zombie z) {
		blood=blood-z.getAttack();
	}
	

}
