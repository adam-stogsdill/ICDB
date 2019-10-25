package bin;

import javax.swing.*;
import java.util.ArrayList;

public class Window extends JPanel {

    private ArrayList<JButton> buttonsArrayList;

    public Window(){
        this.buttonsArrayList = new ArrayList<>();

        for(String option: Settings.buttonOptions()){
            JButton jb = new JButton(option);
            buttonsArrayList.add(jb);
        }


    }

}
