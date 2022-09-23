import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener{ 

    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 600;
    final int SIZE = 30;

    //Snake arrays
    ArrayList<Integer> xSnake = new ArrayList<Integer>();
    ArrayList<Integer> ySnake = new ArrayList<Integer>();

    int score = 0;
    String direction = "right";

    Random random = new Random();
    int appleCoordinateX = random.nextInt(PANEL_WIDTH / SIZE) * SIZE;
    int appleCoordinateY = random.nextInt(PANEL_HEIGHT / SIZE) * SIZE;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    boolean gameOver = false;
    Timer timer;
    int delay = 2;
    int xVelocity = SIZE;
    int yVelocity = 0;
    int x = SIZE;
    int y = SIZE;

    Game(){

        timer = new Timer(125, this);

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Setting up actions:

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new RightAction();
        rightAction = new LeftAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        this.getActionMap().put("upAction", upAction);
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        this.getActionMap().put("downAction", downAction);
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        this.getActionMap().put("rightAction", rightAction);
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        this.getActionMap().put("leftAction", leftAction);

        xSnake.add(x);
        ySnake.add(y);
        xSnake.add(x-SIZE);
        ySnake.add(y);
        xSnake.add(x-2*SIZE);
        ySnake.add(y);

        timer.start();
    }

    public void paint(Graphics g){
        
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        redraw(g2D);
    }

    public void redraw(Graphics2D g){
        
        //Apple
        g.setColor(Color.red);
        g.fillOval(appleCoordinateX , appleCoordinateY ,SIZE, SIZE);

        g.setColor(new Color(175, 227, 41));

        for (int i = xSnake.size() - 1; i >= 1 ; i--){
            switch(direction){
                case "right":
                    xSnake.set(i, xSnake.get(i-1));
                    ySnake.set(i, ySnake.get(i-1));
                    g.fillRect(xSnake.get(i), ySnake.get(i), SIZE, SIZE);
                    break;
    
                case "up":
                    xSnake.set(i, xSnake.get(i-1));
                    ySnake.set(i, ySnake.get(i-1));
                    g.fillRect(xSnake.get(i), ySnake.get(i), SIZE, SIZE);
                    break;
    
                case "left":
                    xSnake.set(i, xSnake.get(i-1));
                    ySnake.set(i, ySnake.get(i-1));
                    g.fillRect(xSnake.get(i), ySnake.get(i), SIZE, SIZE);
                    break;
    
                case "down":
                    xSnake.set(i, xSnake.get(i-1));
                    ySnake.set(i, ySnake.get(i-1));
                    g.fillRect(xSnake.get(i), ySnake.get(i), SIZE, SIZE);
                    break;
            }
        }

        //Snake
        g.setColor(Color.GREEN);
        g.fillRect(x , y , SIZE, SIZE);
        xSnake.set(0, x);
        ySnake.set(0, y);

        // Check if snake head overlaps body then stops running the game:
        snakeOverlap(); 
        if (gameOver){
            timer.stop();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //if snake goes off screen come out other side
        if ( x >= PANEL_WIDTH){
            x = 0;
        }
        if ( x < 0 ){
            x = PANEL_WIDTH;
        }
        if ( y > PANEL_HEIGHT){
            y = 0;
        }
        if ( y < 0 ){
            y = PANEL_HEIGHT;
        }

        // if snake position == apple position:

        if (appleCoordinateX == x && appleCoordinateY == y){
            //new apple coordinates
            appleCoordinateX = random.nextInt(PANEL_WIDTH / SIZE) * SIZE;
            appleCoordinateY = random.nextInt(PANEL_HEIGHT / SIZE) * SIZE;

            score ++;
            grow();
        }
        x += xVelocity;
        y += yVelocity;
        repaint();
        
    }

    //Actions:

    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(overEdge() && !(direction.equals("down"))){
                xVelocity = 0;
                yVelocity = -SIZE;
                direction = "up";
            }
        }
    }
    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(overEdge() && !(direction.equals("up"))){
                xVelocity = 0;
                yVelocity = SIZE;
                direction = "down";
            }   
        }
    }
    public class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(overEdge() && !(direction.equals("right"))){
                xVelocity = SIZE;
                yVelocity = 0;
                direction = "left";
            }
        }
    }
    public class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(overEdge() && !(direction.equals("left"))){
                xVelocity = -SIZE;
                yVelocity = 0;
                direction = "right";
            }        
        }      
    }

    public boolean overEdge(){ //STOPS SNAKE FROM MOVING IF ITS PARTLY OFF SCREEN

        if (x < 0 + SIZE || x > PANEL_WIDTH - SIZE || y < 0 + SIZE|| y > PANEL_HEIGHT - SIZE){
            return false;
        }
        else{
            return true;
        }
    }

    public void grow(){ //Adds onto snake when points are scored
        
        int snakeSize = (xSnake.size()) * SIZE;
        // Last x and y coordinate:
        int yBefore = ySnake.get(ySnake.size() - 1);
        int xBefore = xSnake.get(xSnake.size() - 1);

        switch(direction){
            case "right":
                xSnake.add(x - snakeSize);
                ySnake.add(yBefore);
                break;

            case "up":
                ySnake.add(y + snakeSize);
                xSnake.add(xBefore);
                break;

            case "left":
                xSnake.add(x + snakeSize);
                ySnake.add(yBefore);
                break;

            case "down":
                ySnake.add(y - snakeSize);
                xSnake.add(xBefore);
                break;
        } 
    }

    public void snakeOverlap(){ //If the head of the snake overlaps the body then the gameOver will return true
        for (int i = xSnake.size() - 1; i > 1; i--){
            if (x == xSnake.get(i) && y == ySnake.get(i)){
                gameOver = true;
            }
        }
    }
}