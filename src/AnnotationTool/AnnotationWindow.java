package AnnotationTool;

import AnnotationActions.Finalize;
import bin.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class AnnotationWindow{

    private JPanel configuration;
    private File inputFileLocation;
    private File outputFileLocation;
    private JFrame annotationFrame;
    private boolean isDirectory;

    public AnnotationWindow(File inputFileLocation, File outputFileLocation, boolean isDirectory){
        annotationFrame = new JFrame(inputFileLocation.getAbsolutePath());
        WindowHandler.setCurrentJFrame(annotationFrame);
        this.isDirectory = isDirectory;
        this.inputFileLocation = inputFileLocation;
        this.outputFileLocation = outputFileLocation;

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

        annotationFrame.add(this.configuration, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(1,2));
        JButton doneButton = new JButton("Finalize Actions");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RUN FINALIZATION PROTOCOL");
                try {
                    Finalize.finalizationProtocol();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                WindowHandler.currentJFrame.dispose();
            }
        });

        JButton annotateButton = new JButton("Annotate");
        annotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isDirectory){
                    System.out.println("ANNOTATION OF SINGLE IMAGE!");
                    CategoryWindow categoryWindow = new CategoryWindow(Settings.CATEGORIES);
                    System.out.println(WindowHandler.singleAnnotation);
                }
            }
        });
        southPanel.add(doneButton);
        southPanel.add(annotateButton);
        annotationFrame.add(southPanel, BorderLayout.SOUTH);
        annotationFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        annotationFrame.setUndecorated(true);
        annotationFrame.setVisible(true);
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
