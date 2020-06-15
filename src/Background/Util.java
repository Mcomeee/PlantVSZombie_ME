package Background;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * @author zhizh
 *
 */
public class Util {
	//��ֲ��Ŀ�
	public static final Rectangle REC=new Rectangle(50,0,522,87);
	
	//���տ���
	public static final Rectangle SUNREC=new Rectangle(130,11,46,66);
	//�㶹���ֿ�
	public static final Rectangle PEAREC=new Rectangle(180,11,46,66);
	//�����
	public static final Rectangle NUTREC=new Rectangle(235,11,46,66);
	
	//�Ƿ���ֲ
	public static final boolean PLANTED=true;
	public static final boolean NOPLANT=false;
	
	//ֲ��ı��
	public static final int SUNFLOWER_FLAG=1;
	public static final int PEASHOOTER_FLAG=2;
	public static final int WALLNUT_FLAG=3;
	//�յ��
	public static final int PLANTNULL_FLAG=0;
	
	//��ʬ�ı��
	public static final int NORMAL_FLAG=1;
	public static final int FLAG_FLAG=2;
	//�յ��
	public static final int NULLZOMBIE_FLAG=0;
	
	//ͼƬ֡��
	public static final int SUN_FRAMENUM=22;
	public static final int PEASHOOTER_FRAMENUM=13;
	public static final int SUNFLOWER_FRAMENUM=18;
	
	public static final int NORMAL_FRAMENUM=22;
	public static final int FLAG_FRAMENUM=12;
	
	/*//key->֡��
	public static HashMap<Integer,Integer> zombieMap=new HashMap<>();
	//ͼƬ����->ͼƬ����
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
