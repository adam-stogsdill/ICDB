package bin;

public class Settings {

    public static boolean WHOLE_IMAGE;
    public static boolean BOUNDING_BOX;
    public static boolean GRAYSCALE;
    public static boolean CONVERT_TO_MATRIX;
    public static String separator = ",";


    public static void fullResetValues(){
        WHOLE_IMAGE = false;
        BOUNDING_BOX = false;
        GRAYSCALE = false;
        CONVERT_TO_MATRIX  = false;
        separator = ",";
    }

    public static String[] checkboxOptions(){
        return new String[]{"WHOLE_IMAGE", "BOUNDING_BOX", "GRAYSCALE", "CONVERT_TO_MATRIX"};
    }

    public static void setOptionValue(String str, boolean value){

    }

    public static void setOptionValue(String str, String value){

    }

}
