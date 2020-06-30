package Background;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BgPanel extends JPanel {

    private Image image = null;
    private Image ButtonImage = null;

    BgPanel() {

        this.setLayout(new BorderLayout(10, 5));
        try {
            image = ImageIO.read(new File("graphics/Screen/MainMenu.png"));
            ButtonImage = ImageIO.read(new File("graphics/Screen/Adventure_0.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton button = new JButton();

        ImageIcon ii = new ImageIcon("graphics/Screen/StartButton.png");
        button.setBounds(400, 500, 154, 37);
        button.setIcon(ii);
        this.add(button, BorderLayout.SOUTH);

        button.addActionListener(e -> {
                    // 打开另一个窗口
            try {
                new GameFrame();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        g.clearRect(0, 0, width, height);

        // 画背景图
        g.drawImage(image, 0, 0, width, height, null);
        // 画按钮
        g.drawImage(ButtonImage, 470, 100, 332, 140, this);

    }
}