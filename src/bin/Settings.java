package bin;

public class Settings {

    public static boolean WHOLE_IMAGE;
    public static boolean BOUNDING_BOX;
    public static boolean GRAYSCALE;
    public static boolean CONVERT_TO_MATRIX;
    public static String separator = ",";
    public static String fileLocation = "";


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
        return new String[]{"TEST_BUTTON"};
    }

    public static void setOptionValue(String str, boolean value){

    }

    public static void setOptionValue(String str, String value){

    }

}
