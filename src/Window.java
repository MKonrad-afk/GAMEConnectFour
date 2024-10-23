import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


// The main frame
public class Window extends JFrame {
    public Window() {
        setSize(900, 900);
        setTitle("Connect Four by MK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Board());
        setResizable(false);
        setVisible(true);
    }

    private class Board extends JPanel {
        private final int rows = 6;
        private final int cols = 7;
        private Maze  boardGrid;
        private final int ovalSize = 80;
        private final int spacing = 20;
        private int currentColor = 1;
        private Image backgroundImage;

        public Board() {
            boardGrid =  new Maze();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    handleClick(x, y);
                }
            });
        }

        private void handleClick(int x, int y) {
            int xInit = 100;
            int yInit = 150;

            // Iterate through the grid of ovals to find the clicked oval
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int ovalX = xInit + j * (ovalSize + spacing);
                    int ovalY = yInit + i * (ovalSize + spacing);

                    // click was inside the oval
                    if (x >= ovalX && x <= ovalX + ovalSize && y >= ovalY && y <= ovalY + ovalSize) {
                            boardGrid.putBall(currentColor,i,j);
                            if(currentColor==1)
                                {currentColor=2;}
                            else
                                {currentColor=1;}


                        repaint();
                        return;
                    }
                }
            }
        }


        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            g.fillRect(70, 120, 760, 660);
            // Ovals
            int xInit = 100;
            int yInit = 150;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Settings colors of ovals
                    g.setColor(translateColor(boardGrid.getMaze()[i][j]));
                    g.fillOval(xInit + j * (ovalSize + spacing), yInit + i * (ovalSize + spacing), ovalSize, ovalSize);
                }
            }
            if (boardGrid.checkIfWIN()){
                g.setColor(translateColor(currentColor));
                g.fillRect(0,300, 900,200);
                g.setColor(Color.WHITE);
                setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
                String text = "You won";
                g.drawString(text, 350,400);
            }


            g.setColor(Color.BLACK);
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
            g.drawString("Connect FOUR", 300, 80);
            g.drawString("It's turn -> ",40,830);
            g.setColor(translateColor(currentColor));
            g.fillOval(340,790,50,50);
            g.setColor(Color.BLACK);
            g.drawOval(340,790,50,50);
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
}