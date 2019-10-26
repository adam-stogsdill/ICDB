package AnnotationActions;

import AnnotationTool.WindowHandler;
import bin.Annotation;
import bin.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Finalize {
    public static void finalizationProtocol() throws IOException {
        for(Annotation a: WindowHandler.getAnnotationSet()){
            // This means that the Annotation object is a single image
            if(!a.getCategory().equals("")){
                Files.copy(Paths.get(a.getImage().getAbsolutePath()),Paths.get(Settings.outputLocation + "/" + a.getCategory() + "/" + a.getImage().getName()));
            }
        }
    }
}
