package Zombies;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Bullet.Bullet;

//��ʬ�ĳ�����
/**
 * @author zhizh
 *
 */
public abstract class  Zombie {
	// �����״̬
	public static final int ALIVE = 0;
	public static final int ATTACK = 1;
	public static final int DEAD = 2;
	protected int status = ALIVE;
	
	public void setStatus(int s) {
		status=s;
	}
	
	public boolean isALIVE() {
		return status==ALIVE;
	}
	
	public boolean isATTACK() {
		return status==ATTACK;
	}
	
	public boolean isDEAD() {
		return status==DEAD;
	}
	
	protected int attack;    //��������
	protected Point point=new Point(0,0);		//����
	protected int width;		//���
	protected int length;		//����
	protected int HitPoint;		//����ֵ
	protected int blood;		//Ѫ��
	protected int speed;		//�ٶ�
	public int frameNum;
	
	public Zombie(int width,int length) {
		this.width=width;
		this.length=length;
		Random rand=new Random();
		point.x=1300;
		point.y=rand.nextInt(600-length);
	}
	
	//��ȡͼƬ
	public abstract BufferedImage getImage();
		
	//����ͼƬ
	public void placeImage(Graphics g) {
		g.drawImage(getImage(),point.x,point.y,null);
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
	
	//���ӵ�����
	public void isAttacked(Bullet b) {
		this.blood=this.blood-b.getAttack();
		b.setHit(true);
	}
	
	//��ȡ��ʬ�ľ���
	public Rectangle getZombieRec() {
		return new Rectangle(point.x,point.y,166,144);
	}
	
	//��ʬ����
	public void move() {
		if(status!=ATTACK) {
			point.x-=speed;
		}
	}
	
	

}
