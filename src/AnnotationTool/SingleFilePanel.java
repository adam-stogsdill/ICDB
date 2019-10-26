package AnnotationTool;

import bin.Annotation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SingleFilePanel extends JPanel {

    private File image;
    Annotation annotation;

    public SingleFilePanel(File picture) throws IOException {
        System.out.println("DISPLAYING: " + picture.getAbsolutePath());
        this.image = new File(picture.getAbsolutePath());
        annotation = new Annotation(this.image);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image bufferedImage = null;
        WindowHandler.setSingleAnnotation(annotation);
        try {
            bufferedImage = ImageIO.read(this.image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bufferedImage, 0, 0, null);
        WindowHandler.setSize(bufferedImage.getWidth(null), bufferedImage.getHeight(null) + 50);
    }
}
