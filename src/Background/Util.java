package Background;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/** @author zhizh */
public class Util {
  // 放植物的框
  public static final Rectangle REC = new Rectangle(50, 0, 522, 87);

  // 向日葵框
  public static final Rectangle SUNREC = new Rectangle(130, 11, 46, 66);
  // 豌豆射手框
  public static final Rectangle PEAREC = new Rectangle(180, 11, 46, 66);
  // 坚果框
  public static final Rectangle NUTREC = new Rectangle(235, 11, 46, 66);
  // 寒冰射手框
  public static final Rectangle SNPREC = new Rectangle(285, 11, 46, 66);
  //樱桃
  public static final Rectangle CHERRYEC =new Rectangle(345,11,46,80);
  //345,11,46,60,80
  // 是否被种植
  public static final boolean PLANTED = true;
  public static final boolean NOPLANT = false;

  // ֲ植物的标记
  public static final int SUNFLOWER_FLAG = 1;
  public static final int PEASHOOTER_FLAG = 2;//豌豆射手
  public static final int WALLNUT_FLAG = 3;//坚果
  public static final int SNOWPEASHOOT_FLAG=4;//寒冰射手
  public static final int CHERRYBOMB_FLAG=5;//樱桃炸弹
  // 空点击
  public static final int PLANTNULL_FLAG = 0;

  // 僵尸的标记
  // 普通僵尸
  public static final int NORMAL_FLAG = 1;
  // 旗帜僵尸
  public static final int FLAG_FLAG = 2;
  // 读报僵尸
  public static final int NEWS_FLAG = 3;
  // 空点击
  public static final int NULLZOMBIE_FLAG = 0;

  // 图片帧数
  public static final int SUN_FRAMENUM = 22;
  public static final int PEASHOOTER_FRAMENUM = 13;
  public static final int SUNFLOWER_FRAMENUM = 18;

  public static final int NORMAL_FRAMENUM = 22;
  public static final int FLAG_FRAMENUM = 12;

  /*//key->֡帧数
  public static HashMap<Integer,Integer> zombieMap=new HashMap<>();
  //图片名字->图片缓存
  public static HashMap<String,BufferedImage[]> infos=new HashMap<>();

  static {
  	zombieMap.put(NORMAL_FLAG,NORMAL_FRAMENUM);
  	zombieMap.put(FLAG_FLAG,FLAG_FRAMENUM);

  	File[] files=new File("graphics/Zombies").listFiles();
  	BufferedImage[] imgs;
  	for(File file:files) {
  		try {
  			imgs=new BufferedImage[12];
  			for(int i=0;i<16;i++) {

  			}

  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  	}
  }*/

}