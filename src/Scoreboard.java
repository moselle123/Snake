import java.awt.*;
import javax.swing.*;

public class Scoreboard extends JPanel {

    int score;

    Scoreboard(int score){

        this.setPreferredSize(new Dimension(600,100));
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);

        JLabel scoreTxt = new JLabel("SCORE: ");
        scoreTxt.setForeground(Color.green);

        JLabel scoreNum = new JLabel(String.valueOf(score));
        scoreNum.setForeground(Color.green);

        JLabel highscoreTxt = new JLabel("HIGHSCORE: ");
        highscoreTxt.setForeground(Color.green);

        JLabel highscoreNum = new JLabel("000");
        highscoreNum.setForeground(Color.green);

        JLabel speedTxt = new JLabel("SPEED: ");
        speedTxt.setForeground(Color.green);

        this.add(scoreTxt);
        this.add(scoreNum);
        this.add(highscoreTxt);
        this.add(highscoreNum);
        this.add(speedTxt);

    }

    public void updateScore(int s){
        score = s;
        System.out.print(score);
        revalidate();
        repaint();
    }

    public int getScore(){
        return score;
    }

}
