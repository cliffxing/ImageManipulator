import java.awt.image.BufferedImage;
import greenfoot.*; 
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.awt.Graphics2D;

/**
 * Processor class - class containing methods that processes/alters images passed into it 
 * <p>
 * This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.
 * <p>
 * The class contains several methods to alter given images. To begin, there are six color 
 * methods (red, orange, yellow, green, blue and purple), which shift/change the RGB values
 * of each pixel in the image towards the specified color. There is also a negative method, which 
 * inverts the colors of the image, a grey method, which turns the image grey, a brighten method, which 
 * increases the RGB of each pixel which overall increases the brightness of the image, and a decrease
 * transparency method, which turns the image more transparent as it is called. There is also a cooler
 * and warmer method, which make the image colors "cooler" and "warmer" respectively. Moreover, there
 * are methods that change the contents of the image. The flip horizontal and vertical methods flip the 
 * image horizontally and vertically respectively, and the rotate90 method rotates the image 
 * 90 degrees clockwise. Finally, the class contains methods called unpackPixel() and packagePixel() which extract 
 * the red, green, blue and alpha values out of an int, and put the same four integers back into a 
 * special packed integer. 
 * 
 * @author Cliff Xing
 * @version December 2020
 */
public class Processor  
{

    /**
     * Increases the red RGB values of the image while decreasing the green of
     * blue values to make image "redder"
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage red(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (red < 254)    
                    red += 2;
                if (blue >= 50)
                    blue--;
                if (green >= 50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Increases the red RGB values of the image while making green values closer to '120', and decreasing blue values to
     * make image "oranger"
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage orange(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (red < 200)    
                    red += 4;
                if (green < 120)    
                    green += 2; 
                if (green > 120)    
                    green -=2; 
                if (blue >= 50)
                    blue-=2;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return newBi;
    }

    /**
     * Increases the red and green RGB values of the image while decreasing the blue values to make
     * image "yellower"
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage yellow(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (red < 254)    
                    red += 2;
                if (green < 254)    
                    green += 2;
                if (blue >= 50)
                    blue--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Increases the green RGB values of the image while decreasing red and blue values to make image
     * "greener"
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage green(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (green < 254)    
                    green += 2;
                if (red >= 50)
                    red--;
                if (blue >= 50)
                    blue--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Increases the blue RGB values of the image while decreasing green and red values to make image
     * "bluer". (Credit to Jordan Cohen for the method)
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage blue (BufferedImage bi)
    {

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (blue < 254)    
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Increases the blue RGB values of the image while making red values closer to '153', and decreasing green values to
     * make image "purpler"
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage purple(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (red < 153)    
                    red += 2;
                if (red > 153)    
                    red -= 2; 
                if (blue < 254)    
                    blue += 2;
                if (green>50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return newBi;
    }

    /**
     * Increases green and blue RGB values of image while decreasing red values
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage cooler (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (green  < 254)    
                    green += 2;
                if (blue <254)
                    blue+=2;
                if (red >= 50)
                    red--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return newBi;
    }

    /**
     * Flips the image horizontally using two for loops
     *  
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage flipHorizontal (BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = deepCopy(bi);

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int newColour = packagePixel (rgbValues[1], rgbValues[2], rgbValues[3],  rgbValues[0]);
                newBi.setRGB (xSize-x-1,y, newColour);
            }
        }
        return newBi;
    }

    /**
     * Flips the image vertically using two for loops
     *  
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage verticalFlip(BufferedImage bi)
    {

        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = deepCopy(bi);

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int newColour = packagePixel (rgbValues[1], rgbValues[2], rgbValues[3],  rgbValues[0]);
                newBi.setRGB (x,ySize-y-1, newColour);
            }
        }
        return newBi;
    }

    /**
     * Increases the red RGB values of the image while decreasing the blue and green values of the image
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage warmer(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (red < 200)    
                    red += 2;
                if (blue >= 75)
                    blue--;
                if (green >= 75)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return newBi;
    }

    /**
     * Calculates average RGB value of each pixel and brings each pixel's RGB values closer
     * to the average value
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage greyScale(BufferedImage bi)
    {

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int grey = (blue + red + green)/3;

                if(red> grey)
                {
                    red=red-2;
                }
                if(red< grey)
                {
                    red=red+2;
                }
                if(green> grey)
                {
                    green=green-2;
                }
                if(green< grey)
                {
                    green=green+2;
                }

                if(blue> grey)
                {
                    blue=blue-2;
                }
                if(blue< grey)
                {
                    blue=blue+2;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Sets each RGB value of each pixel to "255-x", where x is the initial
     * RGB value of the pixel to make image negative
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage negative(BufferedImage bi)
    {

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                red=255-red;
                green=255-green;
                blue=255-blue;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return newBi;
    }

    /**
     * Decreases the alpha value of each pixel in the image to decrease
     * the transparency of the image
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage decreaseTransparency(BufferedImage bi)
    {   
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (alpha>25)
                {
                    alpha=alpha-3;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Increases the red, green and blue values of each pixel to make
     * image brighter
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage brighten(BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (green < 170)    
                    green += 2;
                if (blue < 170)    
                    blue += 2;
                if (red < 170)    
                    red += 2;

                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }

        return newBi;
    }

    /**
     * Creates new image and moves pixels to the rotated spot from parameter bi into  
     * new image using two for loops
     * 
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     * 
     * @return BufferedImage The new image that has been altered 
     */ 
    public static BufferedImage rotate90(BufferedImage bi)
    {
        int xSize =  bi.getWidth();
        int ySize = bi.getHeight();

        BufferedImage newBi = new BufferedImage(ySize, xSize, 3); //new image to move into

        for(int x = ySize - 1; x > 0; x--)
        {
            for (int y = xSize - 1; y > 0; y--)
            {
                newBi.setRGB (x, y, bi.getRGB(y, ySize - x));
            }
        }
        return newBi;
    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage.(Credit to 
     * Jordan Cohen for the method)
     *
     * @param newBi The BufferedImage to convert.
     *
     * @return GreenfootImage A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(),newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage. (Credit to 
     * Jordan Cohen for the method)
     *
     * @param bi The BufferedImage to create deep copy of
     *
     * @return BufferedImage A "deep" copy of the BufferedImage passed in the parameter
     */
    public static BufferedImage deepCopy(BufferedImage bi) 
    {

        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation. (Credit to 
     * Jordan Cohen for the method)
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer. (Credit to 
     * Jordan Cohen for the method)
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }

}
