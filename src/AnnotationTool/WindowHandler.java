package AnnotationTool;

import bin.Annotation;

import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class WindowHandler {
    static JFrame currentJFrame;
    static File currentImageFileLocation;
    static Set<Annotation> annotationSet = new HashSet<>();
    static CategoryWindow workingCategoryWindow;
    static Annotation singleAnnotation;


    public static void setCurrentJFrame(JFrame frame){
        currentJFrame = frame;
    }

    public static void setWorkingCategoryWindow(CategoryWindow cw){
        workingCategoryWindow = cw;
    }

    public static void setSingleAnnotation(Annotation a){
        singleAnnotation = a;
    }

    public static void setCurrentImageFileLocation(File fileLocation){
        currentImageFileLocation = fileLocation;
    }

    public static void setSize(int width, int height){
        currentJFrame.setSize(width, height);
    }

    public static Set<Annotation> getAnnotationSet(){
        return annotationSet;
    }

}
