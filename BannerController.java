// Color
import java.awt.Color;

// MouseEvent
import java.awt.event.MouseEvent;

// JLabel, JPanel
import javax.swing.*;

// GRect, GLabel
import acm.graphics.*;

// GraphicsProgram
import acm.program.GraphicsProgram;


/**
 * Sample class that creates a banner mover
 */
public class BannerController extends GraphicsProgram
{
  // foreground padding
  public static final double PADDING = 20.0;

  private BannerMover testBanner;
  private boolean started;

   
  /** Entry point for BannerController program.
    *  
    */
  public static void main(String[] args)
  {
    new BannerController().start(args);
  }


  /**
   * init() instead of constructor for a Graphics program
   */
  public void init()
  {
    // add appropriate mouse listeners
    this.addMouseListeners();

    // set started status to false
    this.started = false;

    // set size to a fixed size (this gives an actual size of 787 x 745 ???)
    this.resize(800, 800);
    this.pause(10);

    // Add a sample JPanel to the top and bottom of the window      
    GLabel topLabel = new GLabel("top");
    GRect topRect   = new GRect(787.0, 15.0 + 8.0);
    topRect.setFilled(true);
    topRect.setColor(Color.GRAY);
    this.add(topRect, 0.0, 0.0);
    this.add(topLabel, 787.0 / 2.0 - topLabel.getWidth() / 2.0, topLabel.getAscent() + 4.0);
    
    GLabel bottomLabel = new GLabel("bottom");
    GRect bottomRect   = new GRect(787.0, 15.0 + 8.0);
    bottomRect.setFilled(true);
    bottomRect.setColor(Color.GRAY);
    this.add(bottomRect, 0, 745.0 - 23.0);
    this.add(bottomLabel, 787.0 / 2.0 - bottomLabel.getWidth() / 2.0, 745.0 - bottomLabel.getDescent() - 4.0);

    // create a background
    GRect background = new GRect(787.0, 745.0 - topRect.getHeight() - bottomRect.getHeight());
    background.setFilled(true);
    background.setFillColor(Color.BLACK);
    this.add(background, 0, 23.0);

    // create a foreground
    GRect foreground = new GRect(background.getWidth() - 2 * PADDING, background.getHeight() - 2 * PADDING);
    foreground.setFilled(true);
    foreground.setFillColor(Color.WHITE);
    this.add(foreground, background.getX() + PADDING, background.getY() + PADDING);

    // create a new BannerMover and give it the canvas
    this.testBanner = new BannerMover(this.getGCanvas());
  }


  /**
   * on mouse click start the banner moving and print the mouse coordinates 
   */
  public void mouseClicked(MouseEvent e)
  {
    if (this.started)
    {
      // stop the BannerMover object
      this.testBanner.stopMoving();
    }
    else
    {
      // change status to started
      this.started = true;

      // start the BannerMover object
      this.testBanner.start();
    }
  }
}
