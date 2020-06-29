package Background;

import Plants.CherryBomb;
import Plants.PotatoMine;
import Plants.RepeaterPea;
import Plants.SnowPeaShooter;
import Plants.SpikeWeed;
import Zombies.BucketheadZombie;
import Zombies.ConeheadZombie;
import Zombies.FootballZombie;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.SourceDataLine;
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

public class GamePanel extends JPanel {
  private List<Sun> sunList = new ArrayList<>(); // 存储太阳
  private Random rand = new Random(); // 随机数
  private Integer SunNum = 100; // ̫太阳的总数
  private Grass[][] grasses = new Grass[5][9]; // 每块地

  private List<Plant> PlantList = new ArrayList<>(); // ֲ植物的集合
  private List<Zombie> ZombieList = new ArrayList<>(); // 僵尸的集合
  private List<Bullet> BulletLi = new ArrayList<>();

  private int flag = 0; // 点击事件
  private int Zombiecnt = 1; // 僵尸出现个数

  public GamePanel() {
    // 初始化每格的草地
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 9; j++) {
        grasses[i][j] = new Grass(250 + j * 86, 70 + i * 94, 86, 94);
      }
    }
    Thread t = new MyThread();
    t.start();
  }

  // 绘制背景图片
  public void drawBackground(Graphics g) {
    try {

      BufferedImage BackImage =
          ImageIO.read(new File("graphics/Items/Background/Background_1.jpg"));
      g.drawImage(BackImage, 0, 0, this);

      BufferedImage ChooseRec = ImageIO.read(new File("graphics/Screen/ChooserBackground.png"));
      g.drawImage(ChooseRec, 50, 0, this);
      g.setFont(new Font("Serif", Font.BOLD, 28));
      g.drawString(SunNum.toString(), 70, 76);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  int BulletTime = 0;
  int SunTime = 0;
  // 绘制植物
  public void drawPlant(Graphics g) {
    for (int i = 0; i < PlantList.size(); i++) {
      Plant plant = PlantList.get(i);
      if (plant instanceof CherryBomb || plant instanceof PotatoMine) {
        // 樱桃，土豆要专门写一个if语句，主要是相判断如果樱桃周围没有图像和周围有僵尸的图像不一样
        for (int k = 0; k < ZombieList.size(); k++) {
          Zombie zom = ZombieList.get(k);
          // 如果二者矩阵位置重合，则代表樱桃爆炸炸掉僵尸
          if (plant.getPlantRec().intersects(zom.getZombieRec())) {
            plant.setStatus(3);
            zom.setStatus(2);
            ZombieList.remove(zom);
          }
        }
      }
      plant.setBullet();
      plant.placeImage(g);

      if (plant instanceof SunFlower) {
        if (SunTime++ % 30 == 0) {
          ((SunFlower) plant).setSun();
          sunList.addAll(((SunFlower) plant).getSun());
          for (int l = 0; l < ((SunFlower) plant).getSun().size(); l++) {
            Sun s = ((SunFlower) plant).getSun().get(l);
            if (s.isIsclick()) ((SunFlower) plant).getSun().remove(s);
          }
        }
      }

      if (plant.getBulletList() != null && BulletTime++ % 5 == 0)
        BulletLi.addAll(plant.getBulletList());
      // 装载子弹
      for (int j = 0; null != plant.getBulletList() && j < plant.getBulletList().size(); j++) {
        Bullet bullet = plant.getBulletList().get(j);
        if (ZombieList.size() >= 1) {
          bullet.placeImage(g);
          bullet.move();
        }
        // 处理子弹
        for (int k = 0; k < ZombieList.size(); k++) {
          Zombie zom = ZombieList.get(k);
          // 如果二者矩阵位置重合，则代表击中
          if (zom.getZombieRec().intersects(bullet.getBullteRec())) {
            plant.getBulletList().remove(bullet);
            zom.isAttacked(bullet);
            // if (zom.getBlood() <= 0) ZombieList.remove(zom);
          }
          if (zom.getBlood() <= 0) {
            zom.setStatus(2);
          }
        }
        if (bullet.isHit()) {
          plant.getBulletList().remove(bullet);
          BulletLi.remove(bullet);
        }
        if (bullet.getPoint().x > 1400) {
          plant.getBulletList().remove(bullet);
          BulletLi.remove(bullet);
        }
      }

      // 僵尸吃植物
      for (int k = 0; k < ZombieList.size(); k++) {
        Zombie zom = ZombieList.get(k);
        // 如果二者矩阵位置重合，则代表僵尸会吃植物,地刺除外

        if (!(plant instanceof SpikeWeed) && plant.getPlantRec().intersects(zom.getZombieRec())) {

          zom.setStatus(1);
          plant.isAttacked(zom);
          if (plant.getBlood() == 0) {
            PlantList.remove(plant);
            zom.setStatus(0);
          }
        }
      }
    }
  }

  // 绘制僵尸
  public void drawZombie(Graphics g) {
    for (int i = 0; null != ZombieList && i < ZombieList.size(); i++) {
      Zombie zom = ZombieList.get(i);
      zom.placeImage(g);
      zom.move();

      if (zom.isDEAD()) {
        int time = zom.getDeadTime();
        zom.setDeadTime(time++);
        ZombieList.remove(zom);
      }
      if (zom.getPoint().x < 100) {
        // g.setColor(Color.RED);
        // g.setFont(new Font("Setif", Font.BOLD, 50));
        // g.drawString("你的脑子被僵尸吃掉了", 330, 220);
        File file = new File("graphics/Screen/bg2.jpg"); // 这里有一个修改的
        try {
          g.drawImage(ImageIO.read(file), 0, 0, null);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // 绘制阳光
  public void drawSun(Graphics g) {
    for (int i = 0; i < sunList.size(); i++) {
      Sun sun = sunList.get(i);
      sun.placeSun(g);
    }
  }
  /* 绘制向日葵产生的阳光
  public void drawFlowerSun(Graphics g) {
    for (int i = 0; i < PlantList.size(); i++) {
      Plant flower = PlantList.get(i);
      if (flower instanceof SunFlower) {
        int x = ((SunFlower) flower).getFlowerSunX() + 10;
        int y = ((SunFlower) flower).getFlowerSunY() + 10;
        for (int j = 0; j < sunList.size(); j++) {
          Sun sun = sunList.get(j);
          sun.placeFlowerSun(g, x, y);
        }
      }
    }
  }*/

  // 阳光回收
  public void moveSun() {
    for (int i = 0; i < sunList.size(); i++) {
      Sun sun = sunList.get(i);
      if (sun.isIsclick()) {
        sun.recover();
        sunList.remove(sun);
        SunNum += 25;
      } else sun.move();
    }
  }

  // 点击阳光——鼠标事件
  public void ClickSun(MouseEvent e) {
    for (Sun sun : sunList) {
      Rectangle rec = sun.getSunRec();
      if (rec.contains(e.getPoint())) {
        sun.setIsclick(true);
      }
    }
  }

  // 绘制选项卡向日葵 豌豆射手 坚果 冰雪射手 樱桃
  public void drawCard(Graphics g) {
    try {
      BufferedImage card_sunflower = ImageIO.read(new File("graphics/Cards/card_sunflower.png"));
      g.drawImage(card_sunflower, 130, 11, 46, 66, this);

      BufferedImage card_peashooter = ImageIO.read(new File("graphics/Cards/card_peashooter.png"));
      g.drawImage(card_peashooter, 180, 11, 46, 66, this);

      BufferedImage card_wallnut = ImageIO.read(new File("graphics/Cards/card_wallnut.png"));
      g.drawImage(card_wallnut, 235, 11, 46, 66, this);

      BufferedImage card_snowpeashooter = ImageIO.read(new File("graphics/Cards/card_snowpea.png"));
      g.drawImage(card_snowpeashooter, 290, 11, 46, 66, this);

      BufferedImage card_cherrybomb = ImageIO.read(new File("graphics/Cards/card_cherrybomb.png"));
      g.drawImage(card_cherrybomb, 345, 11, 46, 66, this);

      BufferedImage card_potatomine = ImageIO.read(new File("graphics/Cards/card_potatomine.png"));
      g.drawImage(card_potatomine, 400, 11, 46, 66, this);

      BufferedImage card_repeashooter =
          ImageIO.read(new File("graphics/Cards/card_repeaterpea.png"));
      g.drawImage(card_repeashooter, 454, 11, 46, 66, this);

      BufferedImage card_spikeweed = ImageIO.read(new File("graphics/Cards/card_spikeweed.png"));
      g.drawImage(card_spikeweed, 509, 11, 46, 66, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 点击选项卡——鼠标事件
  public void clickCard(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      if (Util.SUNREC.contains(e.getPoint())) {
        flag = Util.SUNFLOWER_FLAG;
      }
      if (Util.PEAREC.contains(e.getPoint())) {
        flag = Util.PEASHOOTER_FLAG;
      }
      if (Util.NUTREC.contains(e.getPoint())) {
        flag = Util.WALLNUT_FLAG;
      }
      if (Util.SNPREC.contains(e.getPoint())) {
        flag = Util.SNOWPEASHOOT_FLAG;
      }
      if (Util.CHERRYEC.contains(e.getPoint())) {
        flag = Util.CHERRYBOMB_FLAG;
      }
      if (Util.POTAEC.contains(e.getPoint())) {
        flag = Util.POTATO_FLAG;
      }
      if (Util.REPEAREC.contains(e.getPoint())) {
        flag = Util.REPEASHOOTER_FLAG;
      }
      if (Util.SPIKREC.contains(e.getPoint())) {
        flag = Util.SPIKEWEED_FLAG;
      }

    } else if (e.getButton() == MouseEvent.BUTTON3) {

      flag = Util.PLANTNULL_FLAG;
    }
  }

  // 僵尸的添加方法
  public void addZombie() throws Exception {
    // 有植物没僵尸的情况
    if (PlantList.size() >= 1 && ZombieList.size() < 1) {
      int lastZomY = 0;
      for (int i = 0; i < Zombiecnt; i++) {
        int type = rand.nextInt(30) + 1; // 出现僵尸种类
        int typenum = 0;
        if (type <= 10) typenum = Util.NORMAL_FLAG;
        else if (type <= 15) typenum = Util.FLAG_FLAG;
        else if (type <= 20) typenum = Util.NEWS_FLAG;
        else if (type <= 24) typenum = Util.CONE_FLAG;
        else if (type <= 28) typenum = Util.BUCK_FLAG;
        else typenum = Util.FOOTBALL_FLAG;

        switch (typenum) {
          case Util.NORMAL_FLAG:
            ZombieList.add(new NormalZombie(lastZomY));
            break;
          case Util.FLAG_FLAG:
            ZombieList.add(new FlagZombie(lastZomY));
            break;
          case Util.NEWS_FLAG:
            ZombieList.add(new NewspaperZombie(lastZomY));
            break;
          case Util.CONE_FLAG:
            ZombieList.add(new ConeheadZombie(lastZomY));
            break;
          case Util.BUCK_FLAG:
            ZombieList.add(new BucketheadZombie(lastZomY));
            break;
          case Util.FOOTBALL_FLAG:
            ZombieList.add(new FootballZombie(lastZomY));
            break;
        }
        lastZomY = ZombieList.get(i).getPoint().y;
      }
    }
    if (Zombiecnt < 5) Zombiecnt++;
  }

  // 种植植物
  public void drawImage(Grass grass, int type) throws Exception {
    Plant p;
    switch (type) {
      case Util.SUNFLOWER_FLAG:
        p = new SunFlower(new Point(grass.x, grass.y));
        break;
      case Util.PEASHOOTER_FLAG:
        p = new PeaShooter(new Point(grass.x, grass.y));
        break;
      case Util.WALLNUT_FLAG:
        p = new WallNut(new Point(grass.x, grass.y));
        break;
      case Util.SNOWPEASHOOT_FLAG:
        p = new SnowPeaShooter(new Point(grass.x, grass.y));
        break;
      case Util.CHERRYBOMB_FLAG:
        p = new CherryBomb(new Point(grass.x, grass.y));
        break;
      case Util.POTATO_FLAG:
        p = new PotatoMine(new Point(grass.x, grass.y));
        break;
      case Util.REPEASHOOTER_FLAG:
        p = new RepeaterPea(new Point(grass.x, grass.y));
        break;
      case Util.SPIKEWEED_FLAG:
        p = new SpikeWeed(new Point(grass.x - 3, grass.y + 60));
        break;
      default:
        p = new Null_Plant(new Point(-100, -100));
        break;
    }

    if (SunNum > p.getcost() && !(p instanceof Null_Plant)) {
      grass.setPlanted(Util.PLANTED);
      SunNum -= p.getcost();
      PlantList.add(p);
    }

    // 鼠标归零
    flag = Util.PLANTNULL_FLAG;
    repaint();
  }

  // 种植植物
  public void addPlant(MouseEvent e) throws Exception {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 9; j++) {
        if (grasses[i][j].contains(e.getPoint()) && !grasses[i][j].getPlanted()) {
          drawImage(grasses[i][j], flag);
          return;
        }
      }
    }
  }

  // 僵尸走到地刺上扣血
  public void SpikeRock() {
    for (Plant p : PlantList) {
      // 如果植物是地刺类型就去遍历僵尸集合
      if (p instanceof SpikeWeed) {
        Iterator<Zombie> it = ZombieList.iterator();
        while (it.hasNext()) {
          Zombie z = it.next();
          // 如果僵尸在地刺上就扣血
          if (z.getZombieRec().intersects(p.getPlantRec())) {
            z.loseBlood();
          }

          // 血量低于0，抹掉

          if (z.getBlood() <= 0) {
            z.setStatus(2);
            it.remove();
          }
        }
      }
    }
  }

  class MyThread extends Thread {

    private boolean pause = false;
    private boolean running = true;

    public void run() {
      while (running) {
        try {
          if (pause) continue;
          else {
            moveSun();
            repaint();
          }

          Thread.sleep(100);
        } catch (Exception e) {
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
    while (sunList.size() < 3) {
      sunList.add(
          new Sun(
              new Point((rand.nextInt(800) + 200), -rand.nextInt(100)), rand.nextInt(500) + 200));
    }
    this.addMouseListener(
        new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            clickCard(e);
            ClickSun(e);
            try {
              addPlant(e);
            } catch (Exception exception) {
              exception.printStackTrace();
            }
          }
        });
    drawBackground(g);
    drawCard(g);
    drawPlant(g);
    drawSun(g);
    // drawFlowerSun(g);
    try {
      addZombie();
    } catch (Exception e) {
      e.printStackTrace();
    }
    drawZombie(g);
    SpikeRock();
    if (SunNum < 0) { // hi这里有一个修改的
      g.setColor(Color.RED);
      g.setFont(new Font("Setif", Font.BOLD, 32));
      g.drawString("阳光数不足", 330, 220);
      g.draw3DRect(50, 55, 30, 30, true);
    }
  }
}
