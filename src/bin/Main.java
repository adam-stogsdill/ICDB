package bin;

import javax.swing.*;

public class Main {

    public static int WIDTH = 800;
    public static int HEIGHT = 700;
    public static String title = "ICDB";

    public static void main(String[] args){
        JFrame j = new JFrame();
        Window w = new Window();



        j.add(w);
        j.setTitle(title);
        j.setSize(WIDTH, HEIGHT);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

    }
}
