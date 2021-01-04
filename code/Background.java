import java.awt.image.BufferedImage;
import greenfoot.*;  
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;

/**
 * The XingCliffImage project is a program that can open an image given by the user and
 * perform transformations and color changes to the given image. When done, the new image
 * can be saved with a specified name from the user into the project's folder. The program contains several
 * transformations and color changes, such as: six possible color changes (red, orange, yellow, green, blue 
 * and purple), horizontal and vertical flips, brighten and decrease transparency, cooler or warmer transformations, 
 * 90 degree clockwise rotations, negative and grey scale filters, and an undo/redo feature within the program. Moreover, 
 * along with the custom made buttons and title, the star background is animated and the stars in the back will
 * "shine". 
 * <p>
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files, as well as all of the code required for the
 * "undo" feature. 
 * <p>
 * 
 * @author Cliff Xing
 * @version December 2020
 */
public class Background extends World
{
    //arrayLists:
    private static ArrayList <GreenfootImage> undoList= new ArrayList <GreenfootImage>();
    private static ArrayList <GreenfootImage> redoList= new ArrayList <GreenfootImage>();
    private static ArrayList <GreenfootImage> animationList= new ArrayList <GreenfootImage>();

    // Constants:
    private final String STARTING_FILE = "colorful.jpg";

    // Objects and Variables:
    private TextButton red;
    private TextButton orange;
    private TextButton yellow;
    private TextButton greenify;
    private TextButton blueButton;
    private TextButton purple;
    private ImageHolder image;
    private static int currentIndex; 
    private TextButton hRevButton;
    private TextButton title;
    private TextButton rotate90;
    private TextButton greyButton;
    private TextButton vRevButton;
    private TextButton warmerButton;
    private TextButton negativeButton;
    private TextButton openFile;
    private TextButton coolButton;
    private TextButton dtransparencyButton;
    private TextButton undo;
    private TextButton brightenButton;
    private TextButton colortitle;
    private TextButton savePNG;
    private TextButton imageDisplayer;
    private TextButton redo;
    private String fileName;
    private static int timer;
    private static int currentRedoIndex;
    private static int currentBackground;
    private static boolean canUndo;
    private static boolean canRedo;
    private static int undoCount;
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background() 
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(850, 654, 1); 

        //background animation photos and vairbales
        animationList.add(new GreenfootImage("twinklingstar-0.png"));
        animationList.add(new GreenfootImage("twinklingstar-1.png"));
        animationList.add(new GreenfootImage("twinklingstar-2.png"));
        animationList.add(new GreenfootImage("twinklingstar-3.png"));
        animationList.add(new GreenfootImage("twinklingstar-4.png"));
        animationList.add(new GreenfootImage("twinklingstar-5.png"));
        setBackground(animationList.get(0));
        currentBackground=0;
        setBackground(animationList.get(0));
        timer=0;

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE);
        red= new TextButton (true, "red.png");
        orange= new TextButton (true, "orange.png");
        yellow= new TextButton (true, "yellow.png");
        greenify=new TextButton (true, "green.png");
        blueButton = new TextButton(true,"blue.png");
        purple= new TextButton (true, "purple.png");
        colortitle= new TextButton (true, "colortitle.png");
        dtransparencyButton = new TextButton (true,"decrease-transparency.png");
        savePNG = new TextButton (true,"saveasPNG.png");
        brightenButton= new TextButton (true, "brighten.png");
        title = new TextButton(true,"title.png");
        hRevButton = new TextButton(true,"flip-horizontal.png");
        rotate90 = new TextButton (true,"rotate90.png");
        vRevButton = new TextButton (true,"flip-vertical.png");
        coolButton = new TextButton (true,"cooler.png");
        greyButton = new TextButton(true,"grey-scale.png");
        negativeButton = new TextButton(true,"negative.png");
        undo= new TextButton(true,"undo.png");
        redo = new TextButton (true, "redo.png");
        warmerButton = new TextButton(true, "warmer.png");
        openFile = new TextButton(true,"newfile.png"); 
        imageDisplayer = new TextButton(" [ Current File: " + STARTING_FILE + " ] ");

        // Add objects to the screen
        addObject (red, 640, 180);
        addObject (orange, 720, 180);
        addObject (yellow, 800, 180);
        addObject (greenify, 640, 240);
        addObject (blueButton, 720, 240);
        addObject (purple, 800, 240);
        addObject (colortitle, 725, 125);
        addObject (image, 310, 345);
        addObject(title, 350, 60);
        addObject (blueButton, 670, 220);
        addObject (coolButton, 670, 445);
        addObject (hRevButton, 670, 500);
        addObject (dtransparencyButton, 670, 390);
        addObject (vRevButton, 790, 500);
        addObject( greyButton, 790, 330);
        addObject (rotate90, 725, 560);
        addObject (warmerButton, 790, 435);
        addObject (undo, 310, 120);
        addObject( negativeButton, 670, 330);
        addObject (openFile, 495, 600);
        addObject (imageDisplayer, 310, 600);
        addObject (savePNG, 120, 600);
        addObject (brightenButton, 790,390);
        addObject (redo, 420, 120);

        //undoList variables/objects
        undoList.clear();
        currentIndex=0;
        addToUndoList(image);
        currentIndex--;
        canUndo=false;
        canRedo=false;
        currentRedoIndex=0;
        undoCount=0;
    }

    /**
     * Act() method checks for mouse input and constantly cycles through background photos to 'animate' it
     */
    public void act () 
    {
        checkMouse();
        if (++timer == 7*1)
        {
            changeBackground();
        }

        if (canUndo==false)
        {
            undo.updateImage(true, "cannotundo.png");
        }
        else
        {   
            undo.updateImage(true, "undo.png");
        }

        if (canRedo==true)
        {
            redo.updateImage(true, "redo.png");
        }
        else
        {
            redo.updateImage(true, "cannotundo.png");
            redoList.clear();
        }
    }

    /**
     * Cycles through the different photos of background to give animation effect
     */
    public void changeBackground()
    {
        if (currentBackground<5)
        {
            setBackground(animationList.get(currentBackground+1));
            currentBackground++;
        }

        else if (currentBackground==5)
        {
            setBackground(animationList.get(0));
            currentBackground=0;
        }
        timer=0;
    }

    /**
     * Check for user clicking on a button and performs transformation/action on image depending
     * on what button is clicked
     */
    private void checkMouse () 
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(red))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.red(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(orange))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.orange(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(yellow))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.yellow(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(greenify))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.green(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }            
            else if (Greenfoot.mouseClicked(blueButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.blue(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(purple))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.purple(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(hRevButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.flipHorizontal(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(vRevButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.verticalFlip(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(greyButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.greyScale(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(negativeButton))
            { 
                image.setImage(image.createGreenfootImageFromBI(Processor.negative(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(brightenButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.brighten(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }

            else if (Greenfoot.mouseClicked(undo))
            {
                try
                {
                    addToRedoList(image);
                    image.setImage(undoList.get(currentIndex-1));
                    undoCount++;
                    canRedo=true;
                    undoList.remove(undoList.size()-1);
                    currentIndex--;
                    if (currentIndex==0)
                    {
                        canUndo=false;
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                    canUndo=false;
                }

            }
            else if (Greenfoot.mouseClicked(redo))
            {
                if (canRedo==true)
                {
                    try
                    {
                        undoList.add(redoList.get(redoList.size()-1));
                        redoList.remove(redoList.size()-1);
                        image.setImage(undoList.get(currentIndex+1));
                        currentIndex++;
                        canUndo=true;

                        if (redoList.size()==0)
                        {
                            canRedo=false;
                        }

                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        canRedo=false;

                    }
                }

            }
            else if (Greenfoot.mouseClicked(dtransparencyButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.decreaseTransparency(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(warmerButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.warmer(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(rotate90))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.rotate90(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(coolButton))
            {
                image.setImage(image.createGreenfootImageFromBI(Processor.cooler(image.getBufferedImage())));
                addToUndoList(image);
                canUndo=true;
                canRedo=false;
            }
            else if (Greenfoot.mouseClicked(savePNG))
            {
                savePNG(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(openFile))
            {
                openFile ();
            }
        }
    }

    /**
     * Creates new GreenfootImage of the current image on screen and adds it into
     * "undolist". Also adds 1 to the current Index value
     */
    public static void addToUndoList(ImageHolder image)
    {
        GreenfootImage currentImage = new GreenfootImage(image.getImage());
        undoList.add(currentImage);
        currentIndex++;
    }

    /**
     * Creates new GreenfootImage of the current image on screen and adds it into
     * "redolist"
     */
    public static void addToRedoList(ImageHolder image)
    {
        GreenfootImage currentImage = new GreenfootImage(image.getImage());
        redoList.add(currentImage);
    }

    /**
     * Allows the user to open a new image file. (Credit to 
     * Jordan Cohen for the method)
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name with extension");

        // If the file opening operation is successful, update the text in the open file button
        if (image.openFile (fileName))
        {
            String display = " [  Current File: " + fileName + " ] ";
            imageDisplayer.update (display);
        }

    }

    /**
     * Saves the current image on the screen into file (Credit to 
     * Jordan Cohen for the method)
     * 
     * @param    bi   image to be saved into file
     */
    private void savePNG(BufferedImage bi) 
    {
        String fileName = JOptionPane.showInputDialog("Input file name (no extension)");

        fileName += ".png";
        File f = new File (fileName);
        try
        {
            ImageIO.write(image.getImage().getAwtImage(), "png", f); // need to do some imports
        }
        catch (IOException e)
        {
            System.out.println("Encountered IOException");
        }

    }
}