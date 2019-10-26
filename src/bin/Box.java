package bin;

public class Box {

    private int x, y, width, height;
    private String classification;

    public Box() {
        initialize(0, 0, 0, 0, "");
    }

    public Box(int x, int y, int width, int height, String classification) {
        initialize(x, y, width, height, classification);
    }

    private void initialize(int x, int y, int width, int height, String classification) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.classification = classification;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = x;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = x;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = x;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}