import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class WordSorter
{
  /**
   * Main method to write the random word generated into a file
   */
  public static void main(String[] args) throws FileNotFoundException
  {
    File file = new File(args[0]);
    Scanner in = new Scanner(file);
    File file1 = new File("SmallWord.txt");
    File file2 = new File("MediumWord.txt");
    File file3 = new File("LargeWord.txt");
    PrintWriter print1 = new PrintWriter(file1);
    PrintWriter print2 = new PrintWriter(file2);
    PrintWriter print3 = new PrintWriter(file3);
    while(in.hasNext())
    {
      String word = in.next();
      word = word.replaceAll("\\p{Punct}", "");
      
      if(word.length() >= 3 && word.length() <= 5)
      {
        print1.print(word + " ");
      }
      
      else if(word.length() > 5 && word.length() <= 8)
      {
        print2.print(word + " ");
      }
      
      else if(word.length() > 8)
      {
        print3.print(word + " ");
      }      
    }
    print1.close();
    print2.close();
    print3.close();
  }
}