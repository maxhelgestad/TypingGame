// ArrayList, Random
import java.util.*;

// GCanvas
import acm.graphics.GCanvas;

// Animator
import acm.util.Animator;


/**
 * A  class which creates animated banner on the canvas. 
 */ 
public class BannerMover extends Animator
{
  // where to place the Banner
  private static final double START_X = 50.0;
  private static final double START_Y = 50.0;
  
  // pause time (500 milliseconds)
  private static final int DELAY_TIME = 20;
  
  // default x speed
  private static final double X_SPEED = 1.0;
  
  //*** Current speed Banner only moves horizontally in the X-direction
  public double xSpeed;
  
  // banner object to be animated
  private Banner banner;
  
  // canvas object to add/remove banner to/from
  private GCanvas canvas;
  
  private String nextWord;
  
  public static GUI gui;
  
  
  /**
   * Create a Banner given a canvas.
   */ 
  public BannerMover(GCanvas canvas, String nextWord, GUI gui, double speed)
  {
    // Store reference to canvas
    this.canvas = canvas;
    
    this.nextWord = nextWord;
    
    // set x speed to default x speed
    this.xSpeed = speed;
    
    // Create banner
    this.banner = new Banner(nextWord);
    
    // Add banner to canvas
    this.canvas.add(this.banner, START_X, START_Y);

    //Store reference to gui
    this.gui = gui;
  }
  
  
  /**
   * Run the banner.
   */ 
  public void run()
  {
    // move until Banner disappears or is forced to stop
    while (this.xSpeed > 0 && (this.banner.getX() + this.banner.getWidth()) < (this.canvas.getWidth() - BannerController.PADDING))
    {
      this.banner.move(this.xSpeed, 0);
      
      this.pause(DELAY_TIME);
    }
    if (this.banner.getX() + this.banner.getWidth() >= this.canvas.getWidth() - BannerController.PADDING)
    {
      gui.addStrike();
      gui.callback();
    }   
    // then remove it from the canvas
    this.remove();
  }
  
  
  /**
   * Remove the banner from the canvas.
   */
  public void remove()
  {
    this.canvas.remove(this.banner);
  }
  
  
  /**
   * Allows us to change banner's moving speed.
   *
   * @param x the new x speed
   */ 
  public void setSpeed(double x)
  {
    this.xSpeed = x;
  }
  
  
  /**
   * Allows us to force the banner to stop moving.
   */
  public void stopMoving()
  {
    // banner is stopped when speed is 0
    this.setSpeed(0);
  }
  
  
  /**
   * Allows us to access the text inside the banner.
   *
   * @return the text of the banner
   */ 
  public String getText()
  {
    return this.banner.getMessage();
  }
}
