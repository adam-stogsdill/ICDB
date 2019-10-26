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

    public boolean creatingBox;
    public int topX;
    public int topY;
    public int bottomX;
    public int bottomY;

    public int temptX;
    public int temptY;
    public int tempbx;
    public int tempby;

    public AnnotationWindow(File inputFileLocation, File outputFileLocation, boolean isDirectory){
        annotationFrame = new JFrame(inputFileLocation.getAbsolutePath());
        WindowHandler.setCurrentJFrame(annotationFrame);
        WindowHandler.setAnnotationWindow(this);

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

        WindowHandler.setCurrentJPanel(configuration);

        this.configuration.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(!creatingBox && !Settings.WHOLE_IMAGE){
                    creatingBox = true;
                    topX = e.getX();
                    topY = e.getY();
                    temptX = topX;
                    temptY = topY;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                creatingBox = false;
                bottomX = e.getX();
                bottomY = e.getY();

                System.out.printf("%d, %d, %d, %d\n", topX, topY, bottomX, bottomY);

                configuration.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.configuration.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(creatingBox && !Settings.WHOLE_IMAGE) {
                    tempbx = e.getX();
                    tempby = e.getY();
                    configuration.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        annotationFrame.add(this.configuration, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(1,2));
        if(isDirectory){
            southPanel.setLayout(new GridLayout(2,2));
            JButton left = new JButton("<-");
            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(configuration instanceof DirectoryPanel){
                        ((DirectoryPanel) configuration).decrementAnnotationIndex();
                        configuration.repaint();
                    }
                }
            });
            southPanel.add(left);

            JButton right = new JButton("->");
            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(configuration instanceof DirectoryPanel){
                        ((DirectoryPanel) configuration).incrementAnnotationIndex();
                        configuration.repaint();
                    }
                }
            });
            southPanel.add(right);

        }
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
                System.out.println("ANNOTATION OF SINGLE IMAGE!");
                CategoryWindow categoryWindow = new CategoryWindow(Settings.CATEGORIES);
                System.out.println(WindowHandler.singleAnnotation);
            }
        });
        southPanel.add(doneButton);
        southPanel.add(annotateButton);
        annotationFrame.add(southPanel, BorderLayout.SOUTH);
        int width = (int) ((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * .95);
        int height = (int) ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * .95);
        annotationFrame.setSize(width, height);
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
