package AnnotationTool;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class AnnotationWindow implements MouseListener {

    private JPanel configuration;

    public AnnotationWindow(File f, boolean isDirectory){
        JFrame annotationFrame = new JFrame(f.getAbsolutePath());
        annotationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // If the file is a directory(folder) then load the specific configuration.
        System.out.println("LOADING CONFIGURATION FROM FILE/DIRECTORY");
        if(isDirectory)
            this.initializeDirectoryConfiguration();
        else
            this.initializeSingleFileConfiguration();

        assert(configuration != null);
        System.out.println("CONFIGURATION LOADED SUCCESSFULLY");

    }

    public void initializeDirectoryConfiguration(){
        this.configuration = new DirectoryPanel();
    }

    public void initializeSingleFileConfiguration(){

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
}
