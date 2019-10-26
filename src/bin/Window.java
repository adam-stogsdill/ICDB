package bin;

import AnnotationTool.AnnotationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Window extends JPanel {

    private ArrayList<JCheckBox> checkBoxesArrayList;
    private ArrayList<JButton> jButtonArrayList;
    private JButton fileButton;
    private JButton continueButton;
    private File projectInputFile;
    boolean selectedInputFile;

    public Window(){
        this.checkBoxesArrayList = new ArrayList<>();
        this.jButtonArrayList = new ArrayList<>();

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        fileButton = new JButton("Choose File/Folder");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = jfc.showOpenDialog(fileButton);
                projectInputFile = jfc.getSelectedFile();
                System.out.println(projectInputFile.getAbsolutePath() + " has been choosen!");
                selectedInputFile = true;
            }
        });

        for(String option: Settings.checkboxOptions()){
            JCheckBox jb = new JCheckBox(option);
            checkBoxesArrayList.add(jb);
        }

        for(String buttonOptions: Settings.buttonOptions()){
            JButton jb = new JButton(buttonOptions);
            jButtonArrayList.add(jb);
        }

        this.add(fileButton);

        System.out.println("ADDING CHECKBOX OBJECTS");
        for(JCheckBox checkbox: this.checkBoxesArrayList){
            this.add(checkbox);
        }

        System.out.println("ADDING BUTTON OBJECTS");
        for(JButton jButton: this.jButtonArrayList){
            this.add(jButton);
        }

        System.out.println("ADDING CONTINUE BUTTON");
        continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox jCheckBox: checkBoxesArrayList){
                    Settings.setOptionValue(jCheckBox.getText(), jCheckBox.isSelected());
                }

                if(!selectedInputFile){
                    JOptionPane.showMessageDialog(continueButton, "A FILE HAS NOT BEEN SELECTED!", "File Selection Error",
                            JOptionPane.WARNING_MESSAGE);
                }else {
                    new AnnotationWindow(projectInputFile, projectInputFile.isDirectory());
                }
            }
        });

        this.add(continueButton);
    }

}
