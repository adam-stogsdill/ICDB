package AnnotationTool;

import bin.Annotation;
import bin.Settings;
import bin.Box;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SingleFilePanel extends JPanel  {

    private File image;
    Annotation annotation;
    JButton doneButton;


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

        if(Settings.BOUNDING_BOX){
            ArrayList<Box> boxes = WindowHandler.singleAnnotation.getBoxes();
            for(Box box: boxes){
                g.setColor(Color.GREEN);
                g.drawRect(box.getX(), box.getY(), box.getHeight(), box.getWidth());
            }
        }

        if(WindowHandler.annotationWindow.creatingBox){
            g.setColor(Color.CYAN);
            g.drawRect(WindowHandler.annotationWindow.topX, WindowHandler.annotationWindow.topY, WindowHandler.annotationWindow.bottomX - WindowHandler.annotationWindow.topX, WindowHandler.annotationWindow.bottomY - WindowHandler.annotationWindow.topY);
        }
    }


}
