import java.util.*;
import java.io.*;

public class RandomWord
{
  //new Array List to add words to
  ArrayList<String> list;
  /**
   * Constructor to generate an array list of random words
   * 
   * @param fileName file to generate the list form
   */
  public RandomWord(String fileName) throws FileNotFoundException
  {
    File file = new File(fileName);
    this.list = new ArrayList<String>();
    Scanner in = new Scanner(file);
    while(in.hasNext())
    {
      String word = in.next();
      list.add(word);
    }
  }
  /**
   * Method to generate the next random word
   * 
   * @return String the next random word
   */
  public String next()
  {
    Random whichWord = new Random();
    String nextWord = this.list.get(whichWord.nextInt(this.list.size()));
    return nextWord;
  }
}
