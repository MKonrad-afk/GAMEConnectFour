import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

        public Board() throws IOException {

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    handleClick(x, y);
                    repaint();
                }

            });

            try {
                backgroundImage = ImageIO.read(new File("src/BACKGROUND.png"));
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
            g.setColor(translateColor(findWhereToPaintOval().get(0)));
            g.fillOval(findWhereToPaintOval().get(1),findWhereToPaintOval().get(2),getOvalSize(),getOvalSize());


            //checking winning
            if (checkIfWIN()) {
                g.fillRect(0, 300, 900, 200);
                g.setColor(Color.WHITE);
                setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
                String text = "You won";
                g.drawString(text, 200, 440);
            }


            g.setColor(translateColor(getCurrentColor()));
            g.fillOval(300,790,50,50);
            g.setColor(Color.BLACK);
            g.drawOval(300,790,50,50);
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


    public native List<Integer> findWhereToPaintOval();

    public native boolean checkIfWIN();

    public native int getCurrentColor();
    public native int getOvalSize();

}