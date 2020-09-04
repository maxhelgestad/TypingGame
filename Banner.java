// GCompound, GLabel, GRect
import acm.graphics.*;


/**
 * The Banner class creates a GRect with a Label object inside 
 * containing whatever message is passed in.
 */
public class Banner extends GCompound
{
  // the space between the text and the rectangle
  private static final double PADDING = 4.0;

  // the label
  private GLabel label;


  /**
   * Banner constructor
   */
  public Banner(String message)
  {
    this.label = new GLabel(message);

    this.add(new GRect(this.label.getWidth() + 2 * PADDING, this.label.getHeight() + 2 * PADDING));
    this.add(this.label, PADDING, this.label.getAscent() + PADDING);
  }


  /**
   * getMessage() will return the String inside the Text object of the banner
   */  
  public String getMessage()
  {
    return this.label.getLabel();
  }
}
