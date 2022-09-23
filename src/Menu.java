import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel implements MouseListener{

    JLabel play = new JLabel("PLAY");

    Menu(){
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(new Color(175, 227, 41));
        this.setLayout(null);

        play.setBounds(50, 50, 400, 200);
        play.setForeground(new Color(94, 130, 44));
        play.addMouseListener(this);

        this.add(play);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("clicked");
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        play.setForeground(new Color(55, 94, 0));
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        play.setForeground(new Color(94, 130, 44));
        
    }
}
