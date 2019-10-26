package AnnotationTool;

import bin.Annotation;

import javax.swing.*;
import java.io.File;

public class DirectoryPanel extends JPanel {

    private JButton leftButton;
    private JButton rightButton;
    private File DIRECTORY;
    private Annotation[] annotationList;

    public DirectoryPanel(File directory){
        this.DIRECTORY = directory;

        assert(directory != null);
        annotationList = new Annotation[this.DIRECTORY.listFiles().length];
        File[] tempfilearray = directory.listFiles();

        for(int i = 0; i < annotationList.length; i++){
            assert tempfilearray != null;
            annotationList[i] = new Annotation(tempfilearray[i]);
        }



    }
}
