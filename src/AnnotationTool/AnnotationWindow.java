package AnnotationTool;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class AnnotationWindow{

    private JPanel configuration;
    private File inputFileLocation;
    private File outputFileLocation;
    private JFrame annotationFrame;

    public AnnotationWindow(File inputFileLocation, File outputFileLocation, boolean isDirectory){
        annotationFrame = new JFrame(inputFileLocation.getAbsolutePath());
        WindowHandler.setCurrentJFrame(annotationFrame);

        this.inputFileLocation = inputFileLocation;
        this.outputFileLocation = outputFileLocation;
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
        // If the file is a directory(folder) then load the specific configuration.
        System.out.println("LOADING CONFIGURATION FROM FILE/DIRECTORY");
        if(isDirectory)
            this.initializeDirectoryConfiguration();
        else
            this.initializeSingleFileConfiguration();
        assert(configuration != null);
        System.out.println("CONFIGURATION LOADED SUCCESSFULLY");

        annotationFrame.add(this.configuration);

        annotationFrame.setVisible(true);

    }

    public void initializeDirectoryConfiguration(){
        this.configuration = new DirectoryPanel(this.inputFileLocation);
    }

    public void initializeSingleFileConfiguration(){
        try{
            this.configuration = new SingleFilePanel(this.inputFileLocation);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
