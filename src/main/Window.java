package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

// The main frame
public class Window extends JFrame {
    public Window() throws IOException {
        setSize(900, 900);
        setTitle("Connect Four by MK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Board());
        setResizable(false);
        setVisible(true);
    }

    private class Board extends JPanel {
        private Image backgroundImage;
        private boolean resetting = false;

        public Board() throws IOException {
            setFocusable(true);
            requestFocusInWindow();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    handleClick(x, y);
                    repaint();
                }

            });

            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    changeTheColumn(0);
                    repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    changeTheColumn(1);
                    repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    addAfterAcepting();
                    repaint();
                }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

            try {
                backgroundImage = ImageIO.read(new File("src/main/BACKGROUND.png"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, -5, 0, this.getWidth(), this.getHeight(), this);
            }
            // Ovals
                findWhereToPaintOval(g);

            g.setColor(Color.white);
            // Highlight which column
                findWhereToPaintColumnMark(g);

            //showing current player
            g.setColor(translateColor(getCurrentColor()));
            g.fillOval(300,790,50,50);
            g.setColor(Color.BLACK);
            g.drawOval(300,790,50,50);

            // checking winning
            if (checkIfWIN() && !resetting) {
                resetting = true;
                showRetryDialog();
            }
        }



        private void showRetryDialog() {
            String winColor;
            if (getCurrentColor()==2){
                winColor = "Yellow";
            }
            else {
                winColor="Red";
            }
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Congratulations! \n " +winColor+" won \nWould you like to play again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Retry", "Exit"},
                    "Retry"
            );

            if (choice == JOptionPane.YES_OPTION) {
                clearBoard();
                resetting = false;
            } else {
                System.exit(0);
            }
//            repaint();
        }


        public Color translateColor(int x){
            if(x==0){
                return Color.WHITE;
            } else if (x==1) {
                return Color.yellow;
            }
            else if(x==2){
                return Color.red;
            }
            return Color.BLUE;
        }
    }



    public native void handleClick(int x, int y);

    public native void findWhereToPaintOval(Graphics g);

    public native void addAfterAcepting();

    public native boolean checkIfWIN();

    public native int getCurrentColor();

    public native void findWhereToPaintColumnMark(Graphics g);
    public native void changeTheColumn(int direction);

    public native void clearBoard();

    public native String getBoardInTheStringRepresentation();
}