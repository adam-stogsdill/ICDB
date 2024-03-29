package bin;

import AnnotationTool.AnnotationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Window extends JPanel {

    private ArrayList<JCheckBox> checkBoxesArrayList;
    private ArrayList<JButton> jButtonArrayList;
    private JButton fileButton;
    private JButton outputfileButton;
    private JButton continueButton;
    private File projectInputFile;
    private File projectOutputFile;
    private boolean selectedInputFile;
    private boolean selectedOutputFile;

    Window(){
        BorderLayout parent_bl = new BorderLayout();
        this.checkBoxesArrayList = new ArrayList<>();
        this.jButtonArrayList = new ArrayList<>();

        Font customFont = null;
        Font continueFont = null;
        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./font/rockwen.ttf")).deriveFont(12f);
            continueFont = Font.createFont(Font.TRUETYPE_FONT, new File("./font/rockwen.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            ge.registerFont(continueFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        JFileChooser jfileChooserInput = new JFileChooser();
        jfileChooserInput.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        JPanel NORTH = new DrawNorthPanel();
        NORTH.repaint();

        JPanel Center = new DrawCenterPanel();
        Center.repaint();

        fileButton = new JButton("Choose Input File/Folder");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = jfileChooserInput.showOpenDialog(fileButton);
                projectInputFile = jfileChooserInput.getSelectedFile();
                System.out.println(projectInputFile.getAbsolutePath() + " has been chosen!");
                if(projectInputFile != null)
                    selectedInputFile = true;
            }
        });

        JFileChooser jfileChooserOutput = new JFileChooser();
        jfileChooserOutput.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        outputfileButton = new JButton("Choose Output Folder");
        outputfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder preParsedCategoryString = new StringBuilder();
                int returnVal = jfileChooserOutput.showOpenDialog(outputfileButton);
                projectOutputFile = jfileChooserOutput.getSelectedFile();
                System.out.println(projectOutputFile.getAbsolutePath() + " has been chosen!");
                if(projectOutputFile != null) {
                    selectedOutputFile = true;
                    if(Objects.requireNonNull(projectOutputFile.list()).length != 0){
                        StringBuilder categoryString = new StringBuilder();
                        String[] list = projectOutputFile.list();

                        assert list != null;
                        for(String l: list){
                            categoryString.append(l).append("\n");
                        }

                        int input = JOptionPane.showConfirmDialog(outputfileButton,
                                "Are these your categories? \n" + categoryString, "Category Detection",
                                         JOptionPane.YES_NO_OPTION);

                        if(input == JOptionPane.YES_OPTION)
                            Settings.CATEGORIES = list;
                        else{
                            int yn = JOptionPane.showConfirmDialog(outputfileButton,
                                    "Would you like to set categories now?", "File Selection Error",
                                    JOptionPane.YES_NO_OPTION);
                            while(yn == JOptionPane.YES_OPTION){

                                String categoryInput = JOptionPane.showInputDialog(outputfileButton,
                                        "Enter \"Done\" when you have completed entering Categories.");
                                if(categoryInput.toUpperCase().equals("DONE")){
                                    Settings.setCATEGORIES(preParsedCategoryString.toString().split(","));
                                    String[] cats = preParsedCategoryString.toString().split(",");
                                    System.out.println("CATEGORIES: " + preParsedCategoryString);
                                    break;
                                }
                                preParsedCategoryString.append(categoryInput).append(",");
                            }
                        }
                    }
                }else{
                    int yn = JOptionPane.showConfirmDialog(outputfileButton,
                            "Would you like to set categories now?", "File Selection Error",
                            JOptionPane.YES_NO_OPTION);
                    while(yn == JOptionPane.YES_OPTION) {
                        String categoryInput = JOptionPane.showInputDialog(outputfileButton,
                                "Enter \"Done\" when you have completed entering Categories.");
                        if (categoryInput.toUpperCase().equals("DONE")) {
                            Settings.setCATEGORIES(preParsedCategoryString.toString().split(","));
                            String[] cats = preParsedCategoryString.toString().split(",");
                            System.out.println("CATEGORIES: ");
                            break;
                        }
                        preParsedCategoryString.append(categoryInput).append(",");
                    }
                }
            }
        });

        JPanel checkBoxJPanel = new JPanel();
        GridLayout bl = new GridLayout(5, 2);
        checkBoxJPanel.setLayout(bl);
        for(String option: Settings.checkboxOptions()){
            JCheckBox jb = new JCheckBox(option);
            checkBoxJPanel.add(jb);
            this.checkBoxesArrayList.add(jb);
            jb.setFont(customFont);
        }

        JButton separator = new JButton("Select Separator");
        separator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.separator = JOptionPane.showInputDialog("Separator: ");

            }
        });
        checkBoxJPanel.add(separator);
        separator.setFont(customFont);

        JPanel buttonPanel = new JPanel();
        GridLayout buttonGridLayout = new GridLayout(2,4);
        buttonPanel.setLayout(buttonGridLayout);
        for(String buttonOptions: Settings.buttonOptions()){
            JButton jb = new JButton(buttonOptions);
            buttonPanel.add(jb);
            jb.setFont(customFont);
        }
        buttonPanel.add(fileButton);
        buttonPanel.add(outputfileButton);

        fileButton.setFont(customFont);
        outputfileButton.setFont(customFont);

        System.out.println("ADDING CONTINUE BUTTON");
        continueButton = new JButton("Continue");
        continueButton.setFont(continueFont);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox jCheckBox: checkBoxesArrayList){
                    System.out.println(jCheckBox.getText());
                    Settings.setOptionValue(jCheckBox.getText(), jCheckBox.isSelected());
                }


                if(!selectedInputFile){
                    JOptionPane.showMessageDialog(continueButton, "A FILE HAS NOT BEEN SELECTED!", "File Selection Error",
                            JOptionPane.WARNING_MESSAGE);
                }else if(!Settings.WHOLE_IMAGE && !Settings.BOUNDING_BOX){
                    JOptionPane.showMessageDialog(continueButton, "Either BoundingBox or Whole Image must be selected!", "File Selection Error",
                            JOptionPane.WARNING_MESSAGE);
                }else if(Settings.WHOLE_IMAGE && Settings.BOUNDING_BOX) {
                    JOptionPane.showMessageDialog(continueButton, "BoundingBox and Whole Image cannot be selected at the same time!", "File Selection Error",
                            JOptionPane.WARNING_MESSAGE);
                }else{
                    Settings.setOutputLocation(projectOutputFile);
                    new AnnotationWindow(projectInputFile, projectOutputFile, projectInputFile.isDirectory());
                }
            }
        });



        this.add(continueButton);
        this.add(checkBoxJPanel);
        this.add(buttonPanel);
        this.add(NORTH);
        this.add(Center);
        parent_bl.addLayoutComponent(continueButton, BorderLayout.SOUTH);
        parent_bl.addLayoutComponent(checkBoxJPanel, BorderLayout.WEST);
        parent_bl.addLayoutComponent(buttonPanel, BorderLayout.EAST);
        parent_bl.addLayoutComponent(NORTH, BorderLayout.NORTH);
        parent_bl.addLayoutComponent(Center, BorderLayout.CENTER);
        this.setLayout(parent_bl);


    }

}
