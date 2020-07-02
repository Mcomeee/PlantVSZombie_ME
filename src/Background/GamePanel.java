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
import java.util.*;
import java.util.List;

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

    private int flag = 0; // 选中植物的种类
    private int zombieCnt = 1; // 僵尸出现个数

    GamePanel() {
        // 初始化每格的草地
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                grasses[i][j] = new Grass(250 + j * 86, 70 + i * 94, 86, 94);
            }
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

        long interval = 80;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                action();
                repaint();
            }
        }, interval, interval);

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

    // 植物动作(包括绘制植物图片)
    private void plantAction(Graphics g) {
        for (Plant plant : plantList) {

            plant.placeImage(g);

            if (plant instanceof Bomb) {
                ((Bomb) plant).judgeToBoom(zombieList);
            }

            if (plant instanceof Producer) {
                Sun sun = ((Producer) plant).produce();
                if (sun != null) sunList.add(sun);
            }

            if (plant instanceof Shooter){
                Bullet bullet = ((Shooter) plant).shoot();
                if (bullet != null) bulletList.add(bullet);
            }

        }
    }

    // 绘制子弹
    private void drawBullet(Graphics g){
        for (Bullet bullet : bulletList) {
            bullet.placeImage(g);
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
    private void clickCard(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
            flag = Util.PLANTNULL_FLAG;
            return;
        }

        if (Util.SUNREC.contains(e.getPoint()))
            flag = Util.SUNFLOWER_FLAG;

        if (Util.PEAREC.contains(e.getPoint()))
            flag = Util.PEASHOOTER_FLAG;

        if (Util.NUTREC.contains(e.getPoint()))
            flag = Util.WALLNUT_FLAG;

        if (Util.SNPREC.contains(e.getPoint()))
            flag = Util.SNOWPEASHOOT_FLAG;

        if (Util.CHERRYEC.contains(e.getPoint()))
            flag = Util.CHERRYBOMB_FLAG;

        if (Util.POTAEC.contains(e.getPoint()))
            flag = Util.POTATO_FLAG;

        if (Util.REPEAREC.contains(e.getPoint()))
            flag = Util.REPEASHOOTER_FLAG;

        if (Util.SPIKREC.contains(e.getPoint()))
            flag = Util.SPIKEWEED_FLAG;

    }

        // 僵尸的添加方法
    private void addZombie(){

        if (zombieCnt < 5) zombieCnt++;
        // 有植物没僵尸的情况
        if (plantList.size() < 1 || zombieList.size() >= 1) return;

        Zombie zombie;
        for (int i = 0; i < zombieCnt; i++) {
            int type = rand.nextInt(30) + 1; // 出现僵尸种类
            if (type <= 10) zombie = new NormalZombie();
            else if (type <= 15) zombie = new FlagZombie();
            else if (type <= 20) zombie = new NewspaperZombie();
            else if (type <= 24) zombie = new ConeheadZombie();
            else if (type <= 28) zombie = new BucketheadZombie();
            else zombie = new FootballZombie();

            zombieList.add(zombie);
        }

    }

    // 种植植物
    private void drawImage(Grass grass, int type){ // 这里可以用反射,但是拿不准,怕出事
        Plant plant = null;
        switch (type) {
            case Util.SUNFLOWER_FLAG:
                plant = new SunFlower(new Point(grass.x, grass.y));
                break;
            case Util.PEASHOOTER_FLAG:
                plant = new PeaShooter(new Point(grass.x, grass.y));
                break;
            case Util.WALLNUT_FLAG:
                plant = new WallNut(new Point(grass.x, grass.y));
                break;
            case Util.SNOWPEASHOOT_FLAG:
                plant = new SnowPeaShooter(new Point(grass.x, grass.y));
                break;
            case Util.CHERRYBOMB_FLAG:
                plant = new CherryBomb(new Point(grass.x, grass.y));
                break;
            case Util.POTATO_FLAG:
                plant = new PotatoMine(new Point(grass.x, grass.y));
                break;
            case Util.REPEASHOOTER_FLAG:
                plant = new RepeaterPea(new Point(grass.x, grass.y));
                break;
            case Util.SPIKEWEED_FLAG:
                plant = new SpikeWeed(new Point(grass.x - 3, grass.y + 60));
                break;
        }

        if (plant == null || sumNum < plant.getCost()) return;

        grass.setPlanted(true);
        sumNum -= plant.getCost();
        plantList.add(plant);
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

    // 攻击判定
    private void judgeAttack(){

        for (Zombie zombie : zombieList) {
            // 僵尸吃植物
            for (Plant plant : plantList) {
                if (plant instanceof SpikeWeed || !plant.getRec().intersects(zombie.getZombieRec()))
                    continue;

                zombie.setStatus(1);
                plant.isAttacked(zombie);
                if (plant.getBlood() <= 0)
                    zombie.setStatus(0);

            }

            // 子弹打僵尸
            for (Bullet bullet : bulletList) {
                if (bullet.isHit()) continue;
                if (zombie.getZombieRec().intersects(bullet.getBulletRec())) {
                    zombie.isAttacked(bullet);
                    bullet.setHit(true);
                }
            }
        }
    }

    private void action(){

        zombieList.removeIf(Zombie::isDEAD);
        plantList.removeIf(plant -> !plant.isAlive());
        bulletList.removeIf(bullet -> bullet.isHit() || bullet.getPoint().y > 1200);

        for (Zombie zombie : zombieList) zombie.action();
        for (Plant plant : plantList) plant.action();
        for (Bullet bullet : bulletList) bullet.move();


        addZombie();
        SpikeRock();
        judgeAttack();
        moveSun();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBackground(g);
        drawCard(g);
        plantAction(g);
        drawSun(g);
        drawZombie(g);
        drawBullet(g);
    }
}
