package AnnotationTool;

import bin.Annotation;

import javax.swing.*;
import java.io.File;

public class WindowHandler {
    private static JFrame currentJFrame;
    private static File currentImageFileLocation;
    private static Annotation[] workingAnnotationArray;
    private static CategoryWindow workingCategoryWindow;
    private static Annotation singleAnnotation;


    public static void setCurrentJFrame(JFrame frame){
        currentJFrame = frame;
    }

    public static void setWorkingCategoryWindow(CategoryWindow cw){
        workingCategoryWindow = cw;
    }

    public static void setSingleAnnotation(Annotation a){
        singleAnnotation = a;
    }

    public static void setWorkingAnnotationArray(Annotation[] workingAnnotationArray){
        WindowHandler.workingAnnotationArray = workingAnnotationArray;
    }

    public static void setCurrentImageFileLocation(File fileLocation){
        currentImageFileLocation = fileLocation;
    }

    public static void setSize(int width, int height){
        currentJFrame.setSize(width, height);
    }

}
