package AnnotationTool;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class AnnotationWindow implements MouseListener, KeyListener {

    private JPanel configuration;
    private File file_location;

    public AnnotationWindow(File f, boolean isDirectory){
        JFrame annotationFrame = new JFrame(f.getAbsolutePath());
        annotationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        annotationFrame.setSize(100,100);
        // If the file is a directory(folder) then load the specific configuration.
        System.out.println("LOADING CONFIGURATION FROM FILE/DIRECTORY");
        if(isDirectory)
            this.initializeDirectoryConfiguration();
        else
            this.initializeSingleFileConfiguration();

        assert(configuration != null);
        System.out.println("CONFIGURATION LOADED SUCCESSFULLY");

        annotationFrame.add(this.configuration);
        annotationFrame.pack();
        annotationFrame.setVisible(true);

    }

    public void initializeDirectoryConfiguration(){
        this.configuration = new DirectoryPanel(this.file_location);
    }

    public void initializeSingleFileConfiguration(){
        this.configuration = new SingleFilePanel(this.file_location);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("ANNOTATION OF SINGLE IMAGE!");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
