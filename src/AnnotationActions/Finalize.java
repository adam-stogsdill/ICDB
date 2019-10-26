package AnnotationActions;

import AnnotationTool.WindowHandler;
import bin.Annotation;
import bin.Box;
import bin.Settings;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Finalize {
    public static void finalizationProtocol() throws IOException {
        for(Annotation a: WindowHandler.getAnnotationSet()){
            if(!a.getCategory().equals("")){
                // The Annotation object is a single image
                storeImage(a.getImage(), a.getCategory());
            } else {
                // The Annotation object uses bounding boxes
                // Get parent image
                BufferedImage image = null;
                try {
                    image = ImageIO.read(a.getImage());
                } catch (IOException e) {
                    System.out.println("Image file not found.");
                    throw e;
                }

                // Store subimages
                ArrayList<Box> boxes = a.getBoxes();
                BufferedImage subImage = null;
                for (Box box : boxes) {
                    if (box.getWidth() > 0 && box.getHeight() > 0) {
                        subImage = image.getSubimage(box.getX(), box.getY(), box.getWidth(), box.getHeight());
                        storeImage(subImage, box.getClassification(), getFileExtension(a.getImage())); // Classification is category
                    }
                }
            }
        }
    }

    private static void storeImage(BufferedImage image, String category, String fileExtension) throws IOException {
        System.out.println(fileExtension);
        File imgFile = File.createTempFile("imageBuffer", fileExtension);
        ImageIO.write(image, fileExtension, imgFile);
        storeImage(imgFile, category);
        if(Settings.CONVERT_TO_MATRIX) {
            boolean deleted = imgFile.delete();
            System.out.println("File Deleted? :" + deleted);
        }
    }

    private static void storeImage(File image, String category) throws IOException {
        if (Settings.CONVERT_TO_MATRIX) {
            System.out.println("STORING IN CSV");
            // Store CSV files in category files
            Path categoryFolder = Paths.get(Settings.outputLocation + "/" + category + "/");
            int extensionIndex = image.getName().indexOf('.');
            String name = image.getName();
            if (extensionIndex > 0) {
                name = name.substring(0, image.getName().indexOf("."));
            }
            File csvFile = new File(categoryFolder.toAbsolutePath().toString() + "/" + name + ".csv");
            writeImageToCSV(image, csvFile);
        } else {
            System.out.println("STORING AS IMAGE");
            // Store images in category files
            Files.copy(Paths.get(image.getAbsolutePath()),Paths.get(Settings.outputLocation + "/" + category + "/" + image.getName()));
        }
    }

    private static void writeImageToCSV(File image, File csvFile) throws IOException {
        // Get image data
        ImageHandler ih = new ImageHandler();
        BufferedImage buffImage = null;
        try {
            buffImage = ImageIO.read(image);
        } catch (IOException e) {
            System.out.println("Image file not found");
            throw e;
        }
        int[][][] pixelData = ih.imageToMatrix(buffImage);
        
        // Write data to file
        FileWriter csvWriter = new FileWriter(csvFile);
        int height = pixelData[0].length;
        int width = pixelData.length;
        int depth = pixelData[0][0].length;
        String rgbValues = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rgbValues = "("; 
                for (int z = 0; z < depth - 1; z++) {
                    rgbValues += pixelData[x][y][z];
                    rgbValues += ' ';
                }
                rgbValues += pixelData[x][y][depth - 1];
                rgbValues += ')';
                
                csvWriter.append(rgbValues + ',');
            }
            csvWriter.append('\n');
        }
        csvWriter.flush();
        csvWriter.close();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        } else {
            return "";
        }
    }
}
