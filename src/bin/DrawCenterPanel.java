package bin;

import AnnotationTool.WindowHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DrawCenterPanel extends JPanel {
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("./output-onlinepngtools (2).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bufferedImage, 0, 0, null);
    }
}
