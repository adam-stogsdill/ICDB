package AnnotationTool;

import bin.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bin.Box;

public class CategoryWindow {

    public String[] categories;
    private String output = "";

    public CategoryWindow(String...categories){
        JFrame j = new JFrame();
        JPanel jp = new JPanel();
        for(String c: categories){
            JButton temp = new JButton(c);
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Settings.BOUNDING_BOX){
                        int x = WindowHandler.annotationWindow.temptX;
                        int y = WindowHandler.annotationWindow.temptY;
                        int width = WindowHandler.annotationWindow.tempbx - x;
                        int height = WindowHandler.annotationWindow.tempby - y;
                        WindowHandler.singleAnnotation.addBox(new Box(x, y, width, height, c));
                        System.out.printf("Adding Box: %d, %d ,%s \n", width, height, c);
                        if(!WindowHandler.getAnnotationSet().contains(WindowHandler.singleAnnotation)){
                            WindowHandler.annotationSet.add(WindowHandler.singleAnnotation);
                        }

                    }else {
                        WindowHandler.singleAnnotation.setCategory(c);
                        WindowHandler.annotationSet.add(WindowHandler.singleAnnotation);
                    }
                    WindowHandler.currentJPanel.repaint();
                    j.dispose();
                }
            });

            jp.add(temp);
        }

        j.add(jp);
        j.pack();
        j.setVisible(true);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getOutput() {
        return output;
    }
}
