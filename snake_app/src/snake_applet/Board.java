package snake_applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import snake_applet.Constants;

public class Board extends JPanel implements ActionListener {

    private final int snake_x_position[] = new int[Constants.ALL_LOCS_ON_BOARD];
    private final int snake_y_position[] = new int[Constants.ALL_LOCS_ON_BOARD];

    private int body_of_snake;
    private int food_x_position;
    private int food_y_position;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image food;
    private Image head;


    public Board() {   
      
      //adding listener for changing directions, i.e left, up, right, down for changing directions;
        addKeyListener(new KeyAdapter(){
          public void keyPressed(KeyEvent e) {
              int key = e.getKeyCode();
               //cant turn right if going left
              if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                  leftDirection = true;
                  upDirection = false;
                  downDirection = false;
              }
            //cant turn left if going right
              if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                  rightDirection = true;
                  upDirection = false;
                  downDirection = false;
              }
            //cant turn down if going up
              if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                  upDirection = true;
                  rightDirection = false;
                  leftDirection = false;
              }
              //cant turn up if going down
              if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                  downDirection = true;
                  rightDirection = false;
                  leftDirection = false;
              }
          }
        });
      
        //background of game
        setBackground(Color.white);
        //focus on the game
        setFocusable(true);
        
        //check constants for size
        setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
        
        //load images for the game
        loadImages();
        
        //let us begin
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("/Users/Taku/Documents/workspace/snake_applet/src/snake_applet/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("/Users/Taku/Documents/workspace/snake_applet/src/snake_applet/apple.png");
        food = iia.getImage();

        ImageIcon iih = new ImageIcon("/Users/Taku/Documents/workspace/snake_applet/src/snake_applet/head.png");
        head = iih.getImage();
    }

    //game initializer
    private void initGame() {
      //pieces on the back of the snake, begins with 3 
      body_of_snake = 3;
      
      //set the position of the snake's food for growth or whatever
      setFoodPosition();

        timer = new Timer(Constants.DELAY, this);
        timer.start();
    }

    //randomly sets x and y of the food position
    private void setFoodPosition() {
      int r = (int) (Math.random() * Constants.RAND_POS);
      food_x_position = ((r * Constants.DOT_SIZE));

      r = (int) (Math.random() * Constants.RAND_POS);
      food_y_position = ((r * Constants.DOT_SIZE));
  }
    

    //MOVE THE SNAKE
    private void move() {

        for (int z = body_of_snake; z > 0; z--) {
            snake_x_position[z] = snake_x_position[(z - 1)];
            snake_y_position[z] = snake_y_position[(z - 1)];
        }

        if (leftDirection) {
          snake_x_position[0] -= Constants.DOT_SIZE;
        }

        if (rightDirection) {
          snake_x_position[0] += Constants.DOT_SIZE;
        }

        if (upDirection) {
          snake_y_position[0] -= Constants.DOT_SIZE;
        }

        if (downDirection) {
          snake_y_position[0] += Constants.DOT_SIZE;
        }
    }

    //gets run each time a timer tick happens
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
          //checks if food is eaten
          checkIfFoodEaten();
          //if collided into itself or wall
          checkIfCollided();
          //move either way (unless game over)
          move();
        }

        repaint();
    }
    
    //checks if the food is eaten or not
    private void checkIfFoodEaten() {

      if ((snake_x_position[0] == food_x_position) && (snake_y_position[0] == food_y_position)) {

        body_of_snake++;
        setFoodPosition();
      }
  }

    //essentially checks if you've lost by checking if you bumped into youself or the walls
    private void checkIfCollided() {

      for (int snakeBod = body_of_snake; snakeBod > 0; snakeBod--) {

          if ((snakeBod > 4) && (snake_x_position[0] == snake_x_position[snakeBod]) && (snake_y_position[0] == snake_y_position[snakeBod])) {
              inGame = false;
          }
      }

      if (snake_y_position[0] >= Constants.BOARD_HEIGHT) {
          inGame = false;
      }

      if (snake_y_position[0] < 0) {
          inGame = false;
      }

      if (snake_x_position[0] >= Constants.BOARD_WIDTH) {
          inGame = false;
      }

      if (snake_x_position[0] < 0) {
          inGame = false;
      }
      
      if(!inGame) {
          timer.stop();
      }
      
  }

    
    //uses graphics (drawing functions)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
   // draws the food where it's meant to be, 
   //also updates the drawing to where the snake is meant to be
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(food, food_x_position, food_y_position, this);

            for (int snakeBod = 0; snakeBod < body_of_snake; snakeBod++) {
                if (snakeBod == 0) {
                    g.drawImage(head, snake_x_position[snakeBod], snake_y_position[snakeBod], this);
                } else {
                    g.drawImage(ball, snake_x_position[snakeBod], snake_y_position[snakeBod], this);
                }
            }
        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (Constants.BOARD_WIDTH - metr.stringWidth(msg)) / 2, Constants.BOARD_HEIGHT / 2);
    }

    
}
