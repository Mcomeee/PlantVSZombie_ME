package Background;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import Plants.WallNut;

public class GameFrame extends JFrame{
  public GameFrame() {
    this.setTitle("PlantVSZombie");
    this.setSize(1200,600);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //窗口监听
    //这个是监听的
    this.addWindowListener(new WindowAdapter() {
      //监听器
      public void windowClosing(WindowEvent e) {
        int dialog=JOptionPane.showConfirmDialog(null, "是否退出","退出",JOptionPane.CLOSED_OPTION);
        if(dialog==JOptionPane.OK_OPTION) System.exit(0);
      }
    });

    //音乐播放 -1循环 0不播放 1播放一次
    MusicPlayer musicplayer=new MusicPlayer("bgm.wav");
    musicplayer.loop(0);

    GamePanel panel=new GamePanel();
    this.getContentPane().add(panel);
    this.setVisible(true);

  }

  public static void main(String[] args) {
    StartFrame startFrame = new StartFrame();
    startFrame.setVisible(true);

  }

}
class StartFrame extends JFrame {
  public StartFrame() {
    BgPanel root = new BgPanel();
    this.setContentPane(root);

    root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));

    JButton button = new JButton("begin");
    root.add(button);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // 打开另一个窗口
        new GameFrame();
      }
    });
    this.add(button);
    setSize(400, 300);
    setVisible(true);
    setBackground(Color.GRAY);

  }
}
class BgPanel extends JPanel
{
  Image image = null ;

  public BgPanel()
  {
    URL imageUrl = StartFrame.class.getResource("StartButton1.png");
    try{
      image = ImageIO.read(imageUrl);
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  @Override
  protected void paintComponent(Graphics g)
  {
    int width = this.getWidth();
    int height = this.getHeight();
    g.clearRect(0, 0, width, height);

    // 画背景图
    g.drawImage(image, 0, 0, width, height, null);

    // 加上一层半透明的遮罩
    g.setColor(new Color(255,255,255,200));
    g.fillRect(0, 0, width, height);
  }
}
