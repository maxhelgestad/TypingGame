// BorderLayout, GridLayout
import java.awt.*;

// ActionListener, ActionEvent
import java.awt.event.*;

// JFrame, JButton, JPanel
import javax.swing.*;

import acm.graphics.GCanvas;

import java.io.FileNotFoundException;

import java.lang.InterruptedException;

import java.util.concurrent.TimeUnit;


public class GUI implements ActionListener
{
  //JButton objects
  private JButton easy, medium, hard, start, exit;
  //JTextField object
  private JTextField field;
  //JLabel object
  private JLabel score, strikes, gameOver;
  //GCanvas object
  private GCanvas canvas;
  //BannerMover object
  private BannerMover bm;
  //integers for the score and strikes
  private int totalScore, totalStrikes;
  //RandomWord objects
  public RandomWord word;
  //JFrame object
  private JFrame frame;
  //double speed for words
  public double speed;
  //JPanel objects
  private JPanel buttonPanel, textPanel;
  
  public GUI()
  {
    //Creates Frame
    this.frame = new JFrame();
    this.frame.setSize(750, 500);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Creates canvas
    this.canvas = new GCanvas();
    
    //Creates JPanels
    this.buttonPanel = new JPanel();
    this.textPanel = new JPanel();
    textPanel.setLayout(new GridLayout(3, 1));
    buttonPanel.setLayout(new GridLayout(3, 1));
    
    //Creates start, exit, easy, medium, hard buttons, add action listeners, and adds them to the JFrame
    this.easy = new JButton("Easy");
    this.medium = new JButton("Medium");
    this.hard = new JButton("Hard");
    this.start = new JButton("Start");
    this.exit = new JButton("Exit");
    this.easy.addActionListener(this);
    this.medium.addActionListener(this);
    this.hard.addActionListener(this);
    this.start.addActionListener(this);
    this.exit.addActionListener(this);    
    buttonPanel.add(this.start);
    buttonPanel.add(this.exit);
    buttonPanel.add(this.easy);
    buttonPanel.add(this.medium);
    buttonPanel.add(this.hard);
    
    //creates and adds text field
    this.field = new JTextField(75);
    this.field.addActionListener(this);
    textPanel.add(this.field); 
    
    //creates JLabels for score, strikes, and Game Over
    this.score = new JLabel("Score: " + totalScore);
    this.strikes = new JLabel("Strikes: " + totalStrikes);
    this.gameOver = new JLabel("GAME OVER! YOU LOSE");
    
    textPanel.add(this.score);
    textPanel.add(this.strikes);
    
    //adds buttonPanel to frame
    frame.add(buttonPanel, BorderLayout.NORTH);
    
    this.frame.add(canvas, BorderLayout.CENTER);
    this.frame.add(textPanel, BorderLayout.SOUTH);
    this.frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent event)
  {
    this.easy.setVisible(true);
    this.medium.setVisible(true);
    this.hard.setVisible(true);
    this.start.setVisible(true);
    this.exit.setVisible(true);
    this.field.setVisible(true);
    System.out.println(event.getActionCommand());
    
    //If easy button is selected, words of length 2-5 will be added to the array list. Then, random words will be chosen from list
    //which will be displayed on the GCanvas
    if(event.getSource() == easy )
    {
      this.easy.removeActionListener(this);
      this.medium.removeActionListener(this);
      this.hard.removeActionListener(this);      
      try
      {
        this.word = new RandomWord("SmallWord.txt");
        this.speed = 1.0;
        this.bm = new BannerMover(this.canvas, word.next(), this, this.speed);       
      }
      catch(FileNotFoundException e)
      {
        System.out.println("File doesn't exist");
      }
    }
    //If medium button is selected, words of length 5-8 will be added to the array list. Then, random words will be chosen from list
    //which will be displayed on the GCanvas
    else if(event.getSource() == medium)
    {
      this.easy.removeActionListener(this);
      this.medium.removeActionListener(this);
      this.hard.removeActionListener(this);      
      try
      {
        this.word = new RandomWord("MediumWord.txt");
        this.speed = 2.0;
        this.bm = new BannerMover(this.canvas, this.word.next(), this, this.speed);
      }
      catch(FileNotFoundException e)
      {
        System.out.println("File doesn't exist");
      }
    }
    //If hard button is selected, words of length 8 and greater will be added to the array list. Then, random words will be chosen from list
    //which will be displayed on the GCanvas
    else if(event.getSource() == hard)
    {
      this.easy.removeActionListener(this);
      this.medium.removeActionListener(this);
      this.hard.removeActionListener(this);      
      try
      {
        this.word = new RandomWord("LargeWord.txt");
        this.speed = 3.0;
        this.bm = new BannerMover(this.canvas, this.word.next(), this, this.speed);
      }
      catch(FileNotFoundException e)
      {
        System.out.println("File doesn't exist");
      }
    }
    //Start button starts game, only after mode is selected
    else if(event.getSource() == start)
    {
      this.bm.start();
      this.start.removeActionListener(this);
    }
    //Exits game upon clicking
    else if(event.getSource() == exit)
    {
      System.exit(0);
    }
    
    //Adds strike if user enters incorrect word or if word reaches end of screen, otherwise, they are awarded a point
    else if(event.getSource() == field)
    {
      if(this.bm.getText().equals(field.getText()))
      {
        //this.bm.remove();
        this.bm.stopMoving();
        this.totalScore++;
        this.score.setText("Score: " + totalScore);
        this.bm = new BannerMover(this.canvas, this.word.next(), this, this.speed);
        this.bm.start();
        this.field.setText("");
      }
      else
      {
        this.addStrike();
        this.field.setText("");
      }
    }
  }
  
  //callback method to create a new Banner after one has reached the edge
  public void callback()
  {
    this.bm = new BannerMover(this.canvas, this.word.next(), this, this.speed);
    this.bm.start();
  }
//Adds strike to user if word is entered incorrectly, one user reaches three strikes, a game over JLabel will appear
  public void addStrike()
  {
    this.totalStrikes++;
    this.strikes.setText("Strikes: " + totalStrikes);
    
    if(this.totalStrikes == 3)
    {
      this.bm.remove();
      textPanel.add(this.gameOver);
      this.frame.update(this.frame.getGraphics());
      try
      {        
        TimeUnit.SECONDS.sleep(3);
        System.exit(0);
      }
      catch(InterruptedException e)
      {
      }
    }
  }
//Main method, this starts program
  public static void main(String[] args)
  {
    new GUI();
  }
}