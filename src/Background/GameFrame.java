package Background;

import ReadXML.DataDom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        DataDom frameDom = new DataDom();
        this.setTitle("PlantVSZombie");
        try{
            this.setSize(
                    frameDom.findFrame("GameFrame").get("width"),
                    frameDom.findFrame("GameFrame").get("height")
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 窗口监听
        // 这个是监听的
        this.addWindowListener(new WindowAdapter() {
                // 监听器
                public void windowClosing(WindowEvent e) {
                    int dialog = JOptionPane.showConfirmDialog(
                            null,
                            "是否退出",
                            "退出",
                            JOptionPane.DEFAULT_OPTION
                    );
                    if (dialog == JOptionPane.OK_OPTION) System.exit(0);
                }
        });

        // 音乐播放 -1循环 0不播放 1播放一次
        MusicPlayer musicplayer = new MusicPlayer("bgm.wav");
        musicplayer.loop(0);

        GamePanel panel = new GamePanel();
        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        StartFrame startFrame = new StartFrame();
        startFrame.setVisible(true);
    }
}


