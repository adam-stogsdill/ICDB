package bin;

import javax.swing.*;
import java.util.ArrayList;

public class Window extends JPanel {

    private ArrayList<JCheckBox> checkBoxesArrayList;

    public Window(){
        this.checkBoxesArrayList = new ArrayList<>();

        for(String option: Settings.checkboxOptions()){
            JCheckBox jb = new JCheckBox(option);
            checkBoxesArrayList.add(jb);
        }

        for(JCheckBox checkbox: this.checkBoxesArrayList){
            this.add(checkbox);
        }

    }

}
