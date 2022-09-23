import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;;

public class GamePanel extends JFrame{

    Game game = new Game();
    Menu menu = new Menu();
    JPanel cardPane = new JPanel();
    CardLayout card = new CardLayout();
    //Scoreboard footer = new Scoreboard(gamePanel.getScore());

    GamePanel(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setTitle("SNAKE");
        this.add(game);
        //  cardPane.setLayout(card);
        //cardPane.add(menu , "Menu");
        //cardPane.add(game , "Game"); 
        //this.add(cardPane);       
        this.pack();

        this.setVisible(true);
    }

    public void startGame(){
        card.next(cardPane);
    }
}