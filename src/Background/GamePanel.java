package Background;

import Plants.*;
import Zombies.*;
import Bullet.*;
import Sun.Sun;

import java.awt.*;

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
import javax.swing.JPanel;


public class GamePanel extends JPanel {

    private Random rand = new Random(); // 随机数
    private Integer sumNum = 100; // ̫太阳的总数
    private Grass[][] grasses = new Grass[5][9]; // 每块地

    private List<Sun> sunList = new ArrayList<>(); // 存储太阳
    private List<Plant> plantList = new ArrayList<>(); // ֲ植物的集合
    private List<Zombie> zombieList = new ArrayList<>(); // 僵尸的集合
    private List<Bullet> bulletList = new ArrayList<>();

    private int flag = 0; // 点击事件
    private int zombieCnt = 1; // 僵尸出现个数

    GamePanel() {
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
    private void drawBackground(Graphics g) {
        try {
            // 背景
            BufferedImage backImage = ImageIO.read(
                    new File("graphics/Items/Background/Background_1.jpg")
            );
            g.drawImage(backImage, 0, 0, this);

            // 选择框
            BufferedImage chooseRec = ImageIO.read(
                    new File("graphics/Screen/ChooserBackground.png")
            );
            g.drawImage(chooseRec, 50, 0, this);

            g.setFont(new Font("Serif", Font.BOLD, 28));
            g.drawString(sumNum.toString(), 70, 76);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int bulletTime = 0;
    private int sunTime = 0;

    // 绘制植物
    private void drawPlant(Graphics g) {
        for (int i = 0; i < plantList.size(); i++) {
            Plant plant = plantList.get(i);
            if (plant instanceof Bomb) {
                // 樱桃，土豆要专门写一个if语句，主要是相判断如果樱桃周围没有图像和周围有僵尸的图像不一样
                for (int k = 0; k < zombieList.size(); k++) {
                    Zombie zom = zombieList.get(k);
                    // 如果二者矩阵位置重合，则代表樱桃爆炸炸掉僵尸
                    if (plant.getPlantRec().intersects(zom.getZombieRec())) {
                        plant.setStatus(3);
                        zom.setStatus(2);
                        zombieList.remove(zom);
                    }
                }
            }
            plant.setBullet();
            plant.placeImage(g);

            if (plant instanceof SunFlower) {
                if (sunTime++ % 30 == 0) {
                    ((SunFlower) plant).setSun();
                    sunList.addAll(((SunFlower) plant).getSun());
                    for (int l = 0; l < ((SunFlower) plant).getSun().size(); l++) {
                        Sun s = ((SunFlower) plant).getSun().get(l);
                        if (s.isClicked()) ((SunFlower) plant).getSun().remove(s);
                    }
                }
            }

            if (plant.getBulletList() != null && bulletTime++ % 5 == 0)
                bulletList.addAll(plant.getBulletList());
            // 装载子弹
            for (int j = 0; null != plant.getBulletList() && j < plant.getBulletList().size(); j++) {
                Bullet bullet = plant.getBulletList().get(j);
                if (zombieList.size() >= 1) {
                    bullet.placeImage(g);
                    bullet.move();
                }
                // 处理子弹
                for (int k = 0; k < zombieList.size(); k++) {
                    Zombie zom = zombieList.get(k);
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
                    bulletList.remove(bullet);
                }
                if (bullet.getPoint().x > 1400) {
                    plant.getBulletList().remove(bullet);
                    bulletList.remove(bullet);
                }
            }

            // 僵尸吃植物
            for (int k = 0; k < zombieList.size(); k++) {
                Zombie zom = zombieList.get(k);
                // 如果二者矩阵位置重合，则代表僵尸会吃植物,地刺除外

                if (!(plant instanceof SpikeWeed) && plant.getPlantRec().intersects(zom.getZombieRec())) {

                    zom.setStatus(1);
                    plant.isAttacked(zom);
                    if (plant.getBlood() == 0) {
                        plantList.remove(plant);
                        zom.setStatus(0);
                    }
                }
            }
        }
    }

    // 绘制僵尸
    private void drawZombie(Graphics g) {
        Iterator<Zombie> it = zombieList.iterator();
        while (it.hasNext()){
            Zombie zombie = it.next();
            zombie.placeImage(g);
            zombie.move();
            if (zombie.isDEAD()) {
                int time = zombie.getDeadTime();
                zombie.setDeadTime(time + 1);
                it.remove();
            }

            if (zombie.getPoint().x > 100) continue;

            File file = new File("graphics/Screen/bg2.jpg"); // 这里有一个修改的
            try {
                g.drawImage(ImageIO.read(file), 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 绘制阳光
    private void drawSun(Graphics g) {
        for (Sun sun : sunList) {
            sun.placeSun(g);
        }
    }

    // 阳光回收
    private void moveSun() {
        Iterator<Sun> it = sunList.iterator();
        while (it.hasNext()){
            Sun sun = it.next();
            if (sun.isClicked()) {
                sun.recover();
                it.remove();
                sumNum += 25;
            }
            sun.move();
        }
    }

    // 点击阳光——鼠标事件
    private void clickSun(MouseEvent e) {
        if (e.getButton() != 1) return;
        for (Sun sun : sunList) {
            Rectangle rec = sun.getSunRec();
            if (rec.contains(e.getPoint())) {
                sun.setClicked(true);
            }
        }
    }

    // 绘制选项卡向日葵 豌豆射手 坚果 冰雪射手 樱桃
    private void drawCard(Graphics g) {
        try {
            BufferedImage card_sunflower = ImageIO.read(new File("graphics/Cards/card_sunflower.png"));
            g.drawImage(card_sunflower, 130, 11, 46, 66, this);

            BufferedImage card_peashooter = ImageIO.read(new File("graphics/Cards/card_peashooter.png"));
            g.drawImage(card_peashooter, 180, 11, 46, 66, this);

            BufferedImage card_wallNut = ImageIO.read(new File("graphics/Cards/card_wallnut.png"));
            g.drawImage(card_wallNut, 235, 11, 46, 66, this);

            BufferedImage card_snowPeaShooter = ImageIO.read(new File("graphics/Cards/card_snowpea.png"));
            g.drawImage(card_snowPeaShooter, 290, 11, 46, 66, this);

            BufferedImage card_cherryBomb = ImageIO.read(new File("graphics/Cards/card_cherrybomb.png"));
            g.drawImage(card_cherryBomb, 345, 11, 46, 66, this);

            BufferedImage card_potatoMine = ImageIO.read(new File("graphics/Cards/card_potatomine.png"));
            g.drawImage(card_potatoMine, 400, 11, 46, 66, this);

            BufferedImage card_repeaShooter =
                    ImageIO.read(new File("graphics/Cards/card_repeaterpea.png"));
            g.drawImage(card_repeaShooter, 454, 11, 46, 66, this);

            BufferedImage card_spikeWeed = ImageIO.read(new File("graphics/Cards/card_spikeweed.png"));
            g.drawImage(card_spikeWeed, 509, 11, 46, 66, this);
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
        if (plantList.size() >= 1 && zombieList.size() < 1) {
            int lastZomY = 0;
            for (int i = 0; i < zombieCnt; i++) {
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
                        zombieList.add(new NormalZombie(lastZomY));
                        break;
                    case Util.FLAG_FLAG:
                        zombieList.add(new FlagZombie(lastZomY));
                        break;
                    case Util.NEWS_FLAG:
                        zombieList.add(new NewspaperZombie(lastZomY));
                        break;
                    case Util.CONE_FLAG:
                        zombieList.add(new ConeheadZombie(lastZomY));
                        break;
                    case Util.BUCK_FLAG:
                        zombieList.add(new BucketheadZombie(lastZomY));
                        break;
                    case Util.FOOTBALL_FLAG:
                        zombieList.add(new FootballZombie(lastZomY));
                        break;
                }
                lastZomY = zombieList.get(i).getPoint().y;
            }
        }
        if (zombieCnt < 5) zombieCnt++;
    }

    // 种植植物
    private void drawImage(Grass grass, int type){
        Plant p = null;
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
        }

        if (p == null || sumNum < p.getCost()) return;

        grass.setPlanted(true);
        sumNum -= p.getCost();
        plantList.add(p);
        // 鼠标归零
        flag = Util.PLANTNULL_FLAG;
        repaint();
    }

    // 种植植物
    private void addPlant(MouseEvent e) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (grasses[i][j].contains(e.getPoint()) && !grasses[i][j].isPlanted()) {
                    drawImage(grasses[i][j], flag);
                    return;
                }
            }
        }
    }

    // 僵尸走到地刺上扣血
    private void SpikeRock() {
        for (Plant p : plantList) {
            // 如果植物是地刺类型就去遍历僵尸集合
            if (! (p instanceof SpikeWeed)) continue;

            for (Zombie z : zombieList) {
                // 如果僵尸在地刺上就扣血
                if (z.getZombieRec().intersects(p.getRec()))
                    z.loseBlood();

            }
        }
    }

    class MyThread extends Thread {

        private boolean pause = false;

        public void run() {
            boolean running = true;
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

    @Override
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
                    clickSun(e);
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
        try {
            addZombie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        drawZombie(g);
        SpikeRock();
        if (sumNum < 0) { // hi这里有一个修改的
            g.setColor(Color.RED);
            g.setFont(new Font("Setif", Font.BOLD, 32));
            g.drawString("阳光数不足", 330, 220);
            g.draw3DRect(50, 55, 30, 30, true);
        }
    }
}
