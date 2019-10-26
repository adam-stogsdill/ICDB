package AnnotationTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;

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
                    WindowHandler.singleAnnotation.setCategory(c);
                    WindowHandler.annotationSet.add(WindowHandler.singleAnnotation);
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
