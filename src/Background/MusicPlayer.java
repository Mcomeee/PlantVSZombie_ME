package Background;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class MusicPlayer {
  private Clip clip; // 全局音乐对象

  public MusicPlayer(String filePath) {
    File file = new File(filePath);
    AudioInputStream audio;

    // 当前音频输入流的获取
    try {
      audio = AudioSystem.getAudioInputStream(file);
      clip = AudioSystem.getClip();
      clip.open(audio);
      FloatControl floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
      floatControl.setValue(-30.0f);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // -1循环 0不播放 1播放一次
  public void loop(int type) {
    clip.loop(type);
  }
}
