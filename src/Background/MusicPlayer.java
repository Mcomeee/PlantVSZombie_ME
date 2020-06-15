package Background;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class  MusicPlayer {
	private Clip clip;   //ȫ�����ֶ���
	public MusicPlayer(String filePath) {
		File file=new File(filePath);
		AudioInputStream audio;
		
		//��ǰ��Ƶ�������Ļ�ȡ
		try {
			audio=AudioSystem.getAudioInputStream(file);
			clip=AudioSystem.getClip();
			clip.open(audio);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//-1ѭ�� 0������ 1����һ��
	public void loop(int type) {
		clip.loop(type);
	}

}
