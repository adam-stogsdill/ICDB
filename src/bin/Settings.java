package bin;

import java.io.File;

public class Settings {

    public static boolean WHOLE_IMAGE;
    public static boolean BOUNDING_BOX;
    public static boolean GRAYSCALE;
    public static boolean CONVERT_TO_MATRIX;
    public static String separator = ",";
    public static String fileLocation = "";
    public static String[] CATEGORIES;
    public static File outputLocation;

    public static void fullResetValues(){
        WHOLE_IMAGE = false;
        BOUNDING_BOX = false;
        GRAYSCALE = false;
        CONVERT_TO_MATRIX  = false;
        separator = ",";
    }

    public static String[] checkboxOptions(){
        return new String[]{"WHOLE IMAGE", "BOUNDING BOX", "GRAYSCALE", "CONVERT TO MATRIX"};
    }

    public static String[] buttonOptions(){
        return new String[]{};
    }

    public static void setOptionValue(String str, boolean value){
        System.out.println(str.toLowerCase().replaceAll(" ", "").toString());
        switch(str.toLowerCase().replaceAll(" ", "")){
            case "wholeimage":  WHOLE_IMAGE = value;
                                break;
            case "boundingbox": BOUNDING_BOX = value;
                                break;
            case "grayscale":   GRAYSCALE = value;
                                break;
            case "converttomatrix": CONVERT_TO_MATRIX = value;
                                break;
            default:    System.out.println("INVALID SETTING CALLED");
                        break;
        }
    }



    public static void setOptionValue(String str, String value){

    }

    public static void setCATEGORIES(String[] categories) {CATEGORIES = categories;}

    public static void setOutputLocation(File ol){
        outputLocation = ol;
    }
}
