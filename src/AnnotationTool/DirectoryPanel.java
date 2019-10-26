package AnnotationTool;

import javax.swing.*;
import java.io.File;

public class DirectoryPanel extends JPanel {

    private JButton leftButton;
    private JButton rightButton;
    private File DIRECTORY;

    public DirectoryPanel(File directory){
        File[] fileList = directory.listFiles();

    }
}
