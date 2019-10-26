package AnnotationTool;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class AnnotationWindow{

    private JPanel configuration;
    private File file_location;

    public AnnotationWindow(File f, boolean isDirectory){
        JFrame annotationFrame = new JFrame(f.getAbsolutePath());
        annotationFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!isDirectory && e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println("ANNOTATION OF SINGLE IMAGE!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        annotationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        annotationFrame.setSize(100,100);
        this.file_location = f;
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

}
