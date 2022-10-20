import javax.swing.JFrame;

public class GamePanel extends JFrame{

    Game game = new Game();

    GamePanel(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setTitle("SNAKE");
        this.add(game);     
        this.pack();

        this.setVisible(true);
    }
}