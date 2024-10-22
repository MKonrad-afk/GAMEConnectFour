import javax.swing.*;
import java.awt.*;

//The main frame
public class Window extends JFrame {
    public Window(){
        setSize(900,900);
        setTitle("Connect Four by MK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);
        add(new Board());
        setResizable(false);
        setVisible(true);
    }

    //Creation of the container that has elements of the maze inside
    private class Board extends JPanel{

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLUE);
            g.fillRect(70,120,760,660);

            g.setColor(Color.white);
            int xInit = 100;
            int yInit =150;
            for(int i =0 ; i< 6;i++) {
                for(int j=0;j<7;j++) {
                    g.fillOval(xInit, yInit, 80, 80);
                    xInit+=100;
                }
                xInit= 100;
                yInit+=100;
            }
        }
    }

}

