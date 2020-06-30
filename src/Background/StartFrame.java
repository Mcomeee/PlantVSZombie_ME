package Background;

import ReadXML.DataDom;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    StartFrame() throws Exception {
        DataDom frameDom = new DataDom();
        setSize(
                frameDom.findFrame("StartFrame").getFrameWidth(),
                frameDom.findFrame("StartFrame").getFrameHeight()
        );
        setVisible(true);
        setBackground(Color.GRAY);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BgPanel root = new BgPanel();
        this.setContentPane(root);
        this.setVisible(true);
        root.setLayout(null);
    }
}