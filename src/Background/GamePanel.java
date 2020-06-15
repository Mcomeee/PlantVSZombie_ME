package Background;

import Zombies.NewspaperZombie;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Bullet.Bullet;
import Bullet.PeaBullet;
import Plants.Null_Plant;
import Plants.PeaShooter;
import Plants.Plant;
import Plants.SunFlower;
import Plants.WallNut;
import Sun.Sun;
import Zombies.FlagZombie;
import Zombies.NormalZombie;
import Zombies.Zombie;

public class GamePanel extends JPanel{
	List<Sun> sunList=new ArrayList<>();		//�洢̫��
	Random rand=new Random();		//�����
	private Integer SunNum=100;		//̫��������
	private Grass grass[]=new Grass[45];		//ÿ���
	
	List<Plant> PlantList=new ArrayList<>();		//ֲ��ļ���
	List<Zombie> ZombieList=new ArrayList<>();		//��ʬ�ļ���
	List<Bullet> BulletLi=new ArrayList<>();
	
	int flag=0;		//����¼�
	int Zombiecnt=1;		//��ʬ���ָ���
	
	public GamePanel() {
		//��ʼ��ÿ��Ĳݵ�
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				grass[i+j*9]=new Grass(250+i*65,70+j*96,65,96);
			}
		}
		Thread t=new MyThread();
		t.start();
	}
	
	/*public void StartGame() {
		new MyThread().start();;
		new PlantTimeThread().start();

	}*/
	
	
	//���Ʊ���ͼƬ
	public void drawBackground(Graphics g) {
		try {
			BufferedImage BackImage=ImageIO.read(new File("graphics/Items/Background/Background_0.jpg"));
			g.drawImage(BackImage, 0, 0, this);
			BufferedImage ChooseRec=ImageIO.read(new File("graphics/Screen/ChooserBackground.png"));
			g.drawImage(ChooseRec,50,0,this);
			g.setFont(new Font("Serif",Font.BOLD,32));
			g.drawString(SunNum.toString(), 59, 70);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	int BulletTime = 0;
	//����ֲ��
	public void drawPlant(Graphics g) {
		for(int i=0;i<PlantList.size();i++) {
			Plant plant=PlantList.get(i);
			
			
			plant.setBullet();
			plant.placeImage(g);
				
			if(plant.getBulletList()!=null&&BulletTime++%5==0)
				BulletLi.addAll(plant.getBulletList());
				//װ���ӵ�
			for(int j=0;null!=plant.getBulletList()&&j<plant.getBulletList().size();j++) {
				Bullet bullet=plant.getBulletList().get(j);
				bullet.placeImage(g);
				bullet.move();
				//�����ӵ�
				for(int k=0;k<ZombieList.size();k++) {
					Zombie zom=ZombieList.get(k);
					//������߾���λ���غ�,��������
					if(bullet.getBullteRec().intersects(zom.getZombieRec())) {
						plant.getBulletList().remove(bullet);
						zom.isAttacked(bullet);
						if(zom.getBlood()==0) ZombieList.remove(zom);
					}
				}
				if(bullet.isHit()) {
					plant.getBulletList().remove(bullet);
					BulletLi.remove(bullet);
				}
				if(bullet.getPoint().x>1400) {
					plant.getBulletList().remove(bullet);
					BulletLi.remove(bullet);
				}
			}

			
			//��ʬ��ֲ��
			for(int k=0;k<ZombieList.size();k++) {
				Zombie zom=ZombieList.get(k);
				//������߾���λ���غϣ������ʬ���ֲ��
				if(plant.getPlantRec().intersects(zom.getZombieRec())) {
					zom.setStatus(1);
					plant.isAttacked(zom);
					if(plant.getBlood()==0){
						PlantList.remove(plant);
						zom.setStatus(0);
					}
				}
			}
			
		}
	}
		
	//���ƽ�ʬ
	public void drawZombie(Graphics g) {
		for(int i=0;null!=ZombieList&&i<ZombieList.size();i++) {
			Zombie zom=ZombieList.get(i);
			zom.placeImage(g);
			zom.move();
			
			if(zom.getPoint().x<100) {
				g.setColor(Color.RED);
				g.setFont(new Font("Setif",Font.BOLD,50));
				g.drawString("������ӱ���ʬ�Ե���", 330, 220);
			}
		}
	}
	
	//��������
	public void drawSun(Graphics g) {
		for(int i=0;i<sunList.size();i++) {
			Sun sun=sunList.get(i);
			sun.placeSun(g);
		}
	}
	
	//�������
	public void moveSun() {
		for(int i=0;i<sunList.size();i++) {
			Sun sun=sunList.get(i);
			if(sun.isIsclick()) {
				sun.recover();
				sunList.remove(sun);
				SunNum+=25;
			}else sun.move();
			
		}
	}
	
	//������⡪������¼�
	public void ClickSun(MouseEvent e) {
		for(Sun sun:sunList) {
			Rectangle rec=sun.getSunRec();
			if(rec.contains(e.getPoint())) {
				sun.setIsclick(true);
			}
		}
	}
	
	
	//����ѡ�
	public void drawCard(Graphics g) {
		try {
			BufferedImage card_sunflower=ImageIO.read(new File("graphics/Cards/card_sunflower.png"));
			g.drawImage(card_sunflower, 130, 11, 46,66,this);
			BufferedImage card_peashooter=ImageIO.read(new File("graphics/Cards/card_peashooter.png"));
			g.drawImage(card_peashooter, 180, 11, 46,66,this);
			BufferedImage card_wallnut=ImageIO.read(new File("graphics/Cards/card_wallnut.png"));
			g.drawImage(card_wallnut, 235, 11, 46,66,this);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	//���ѡ���������¼�
	public void clickCard(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			if(Util.SUNREC.contains(e.getPoint())) {
				flag=Util.SUNFLOWER_FLAG;
			}
			if(Util.PEAREC.contains(e.getPoint())) {
				flag=Util.PEASHOOTER_FLAG;
			}
			if(Util.NUTREC.contains(e.getPoint())) {
				flag=Util.WALLNUT_FLAG;
			}
		}
	}
	
	//��ʬ����ӷ���
	public void addZombie() {
		//��ֲ��û��ʬ�����
		if(PlantList.size()>=1&&ZombieList.size()<1) {
			for(int i=0;i<Zombiecnt;i++) {
				int type=rand.nextInt(2)+1;		//���ֽ�ʬ����
				if(type==1) ZombieList.add(new NormalZombie());
				else if(type==2) ZombieList.add(new FlagZombie());
				else if(type==3) ZombieList.add(new NewspaperZombie());
			}
		}
		if(Zombiecnt<5) Zombiecnt++;
	}
	
	//��ֲֲ��
	public void drawImage(int index,int type) {
		Plant p;
		
		if(type==Util.SUNFLOWER_FLAG) p=new SunFlower(new Point(grass[index].x,grass[index].y));
		else if(type==Util.PEASHOOTER_FLAG) p=new PeaShooter(new Point(grass[index].x,grass[index].y));
		else if(type==Util.WALLNUT_FLAG) p=new WallNut(new Point(grass[index].x,grass[index].y));
		else p=new Null_Plant(new Point(-100,-100));
		
		grass[index].setPlanted(Util.PLANTED);
		SunNum-=p.getcost();
		PlantList.add(p);
		
		//������
		flag=Util.PLANTNULL_FLAG;
		repaint();
	}
	
	//��ֲֲ��
	public void addPlant(MouseEvent e) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<5;j++) {
				if(grass[i+j*9].contains(e.getPoint()) && !grass[i+j*9].getPlanted()) {
					drawImage(i+j*9,flag);
				}
			}
		}
	}
	
	class MyThread extends Thread{
		
		private boolean pause = false;
		private boolean running = true;
		public void run() {
			while(running) {
				try {
					if(pause) continue;
					else {
						moveSun();
					    repaint();
					}
					
					Thread.sleep(100);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		public void pause() {
			this.pause = true;

		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		while(sunList.size()<3) {
			sunList.add(new Sun(new Point((rand.nextInt(800)+200),-rand.nextInt(100)),rand.nextInt(500)+200));
		}
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				clickCard(e);
				ClickSun(e);
				addPlant(e);
			}
		});
		drawBackground(g);
		drawCard(g);
		drawPlant(g);
		drawSun(g);
		addZombie();
		drawZombie(g);
	}
}
