package AnnotationTool;

import javax.swing.*;
import java.util.ArrayList;

public class CategoryWindow {
    public String[] categories;
    private ArrayList<JButton> jb;

    public CategoryWindow(String...categories){
        JFrame j = new JFrame();
        for(String c: categories){
            JButton temp = new JButton(c);
            this.jb.add(temp);
        }


    }

}
