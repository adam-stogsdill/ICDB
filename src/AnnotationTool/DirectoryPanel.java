package AnnotationTool;

import bin.Annotation;
import bin.Box;
import bin.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DirectoryPanel extends JPanel {

    private JButton leftButton;
    private JButton rightButton;
    private File DIRECTORY;
    private Annotation[] annotationList;
    private int annotationIndex = 0;

    public DirectoryPanel(File directory){
        this.DIRECTORY = directory;

        assert(directory != null);
        annotationList = new Annotation[this.DIRECTORY.listFiles().length];
        File[] tempfilearray = directory.listFiles();

        for(int i = 0; i < annotationList.length; i++){
            assert tempfilearray != null;
            annotationList[i] = new Annotation(tempfilearray[i]);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image bufferedImage = null;
        WindowHandler.setSingleAnnotation(this.annotationList[annotationIndex]);
        System.out.println(this.annotationList[this.annotationIndex].getImage().getName());
        try {
            bufferedImage = ImageIO.read(this.annotationList[this.annotationIndex].getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bufferedImage, 0, 0, null);

        if(Settings.BOUNDING_BOX){
            ArrayList<bin.Box> boxes = WindowHandler.singleAnnotation.getBoxes();
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

    public void incrementAnnotationIndex(){
        if(this.annotationIndex < this.annotationList.length - 1)
            this.annotationIndex++;
    }

    public void decrementAnnotationIndex(){
        if(this.annotationIndex >= 1)
            this.annotationIndex--;
    }
}
