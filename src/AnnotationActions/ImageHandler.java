package AnnotationActions;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

class ImageHandler {

    public static enum Direction {
        CLOCKWISE, COUNTER_CLOCKWISE, FLIP_HORIZONTAL, FLIP_VERTICAL
    }

    public ImageHandler() {

    }

    public BufferedImage rotateImage(BufferedImage image, Direction direction) {
        int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        int[][] pixels2D = makePixels2D(pixels, image.getWidth());

        pixels2D = rotatePixels(pixels2D, direction);

        return createImage(pixels2D);
    }

    public int[][][] imageToMatrix(BufferedImage image) {
        boolean isGrayscale = isGrayscale(image);
        int width = image.getWidth();
        int height = image.getHeight();
        int depth = isGrayscale ? 1 : 3;
        int[][][] matrix = new int[width][height][depth];

        int pixel, red, green, blue;
        for (int col = 0; col < height; col++) {
            for (int row = 0; row < width; row++) {
                pixel = image.getRGB(row, col);

                if (isGrayscale) {
                    matrix[row][col][depth] = pixel & 0xff;
                } else {
                    red = (pixel >> 16) & 0xff;
                    green = (pixel >> 8) & 0xff;
                    blue = (pixel) & 0xff;
                    matrix[row][col][0] = red;
                    matrix[row][col][1] = green;
                    matrix[row][col][2] = blue;
                }
            }
        }

        return matrix;
    }

    public boolean isGrayscale(BufferedImage image) {
        // Check is formatted as gray
        if (image.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            return true;
        }

        boolean isGrayscale = true;
        int pixel, red, green, blue;
        // Check if all RGB values are the same
        for (int col = 0; col < image.getHeight(); col++) {
            for (int row = 0; row < image.getWidth(); row++) {
                pixel = image.getRGB(row, col);
                red = (pixel >> 16) & 0xff;
                green = (pixel >> 8) & 0xff;
                blue = (pixel) & 0xff;

                if (red != green || green != blue) {
                    isGrayscale = false;
                }
            }

            if (!isGrayscale) {
                break;
            }
        }

        return isGrayscale;
    }

    private int[][] rotatePixels(int[][] pixels, Direction direction) {
        int pWidth = pixels.length;
        int pHeight = pixels[0].length;
        int newWidth = pHeight;
        int newHeight = pWidth;
        int[][] newPixels = new int[newWidth][newHeight];

        for (int row = 0; row < pHeight; row++) {
            for (int col = 0; col < pWidth; col++) {
                switch (direction) {
                case CLOCKWISE:
                    newPixels[col][newWidth - row - 1] = pixels[row][col];
                    break;
                case COUNTER_CLOCKWISE:
                    newPixels[newWidth - row - 1][col] = pixels[col][row];
                    break;
                case FLIP_HORIZONTAL:
                    newPixels[col][newWidth - row - 1] = pixels[col][row];
                    break;
                case FLIP_VERTICAL:
                    newPixels[newWidth - pHeight - 1][col] = pixels[row][col];
                    break;
                }
            }
        }
        return newPixels;
    }


    private BufferedImage createImage(int[][] pixels) {
        int pWidth = pixels.length;
        int pHeight = pixels[0].length;
        BufferedImage outputImage = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_RGB);
        int[] outputImagePixelData = ((DataBufferInt) outputImage.getRaster().getDataBuffer()).getData();

        for (int y = 0, pos = 0; y < pHeight; y++)
            for (int x = 0; x < pWidth; x++, pos++)
                outputImagePixelData[pos] = pixels[y][x];

        return outputImage;
    }

    private int[][] makePixels2D(int[] pixels, int numColumns) {
        int width = numColumns;
        int height = pixels.length / width;
        int[][] output = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                output[x][y] = pixels[x + numColumns * y];
            }
        }

        return output;
    }

}