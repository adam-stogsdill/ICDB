package bin;

import java.io.File;
import java.util.ArrayList;

public class Annotation {

    private File image;
    private ArrayList<Box> boxes;

    public Annotation(File image) {
        initialize(image, new ArrayList<Box>());
    }

    public Annotation(File image, ArrayList<Box> boxes) {
        initialize(image, boxes);
    }

    private void initialize(File image, ArrayList<Box> boxes) {
        this.image = image;
        this.boxes = boxes;
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public void removeBox(Box box) {
        boxes.remove(box);
    }

    public void removeBox(int index) {
        boxes.remove(index);
    }

    public File getImage() {
        return this.image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public ArrayList<Box> getBoxes() {
        return this.boxes;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }

}