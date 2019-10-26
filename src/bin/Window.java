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
        BorderLayout parent_bl = new BorderLayout();
        GridLayout bl = new GridLayout(5, 10);
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

        JPanel checkBoxJPanel = new JPanel();
        checkBoxJPanel.setLayout(bl);
        for(String option: Settings.checkboxOptions()){
            JCheckBox jb = new JCheckBox(option);
            checkBoxJPanel.add(jb);
        }


        JPanel buttonPanel = new JPanel();
        GridLayout buttonGridLayout = new GridLayout(2,4);
        buttonPanel.setLayout(buttonGridLayout);
        for(String buttonOptions: Settings.buttonOptions()){
            JButton jb = new JButton(buttonOptions);
            buttonPanel.add(jb);
        }
        buttonPanel.add(fileButton);



        /*System.out.println("ADDING BUTTON OBJECTS");
        for(JButton jButton: this.jButtonArrayList){
            this.add(jButton, BorderLayout.EAST);
            parent_bl.addLayoutComponent(jButton, BorderLayout.EAST);
        }*/

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
        this.add(checkBoxJPanel);
        this.add(buttonPanel);
        parent_bl.addLayoutComponent(continueButton, BorderLayout.SOUTH);
        parent_bl.addLayoutComponent(checkBoxJPanel, BorderLayout.WEST);
        parent_bl.addLayoutComponent(buttonPanel, BorderLayout.EAST);
        this.setLayout(parent_bl);


    }

}
