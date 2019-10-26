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
                System.out.println(box);
                g.setColor(Color.GREEN);
                g.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
            }
        }

        if(WindowHandler.annotationWindow.temptX != -1) {
            g.setColor(Color.CYAN);
            System.out.println((WindowHandler.annotationWindow.tempbx - WindowHandler.annotationWindow.temptX) + " " + (WindowHandler.annotationWindow.tempby - WindowHandler.annotationWindow.temptY));
            g.drawRect(WindowHandler.annotationWindow.temptX, WindowHandler.annotationWindow.temptY, WindowHandler.annotationWindow.tempbx - WindowHandler.annotationWindow.temptX, WindowHandler.annotationWindow.tempby - WindowHandler.annotationWindow.temptY);

        }



    }


}
