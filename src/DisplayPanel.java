import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener {
    private int score;
    private boolean yellowColor;
    private int marioX;
    private int marioY;
    private int ratX;
    private int ratY;
    private BufferedImage background;
    private BufferedImage mario;
    private BufferedImage pizza;
    private BufferedImage rat;
    private BufferedImage basil;



    public DisplayPanel() {
        score = 0;
        yellowColor = true;
        marioX = 230;
        marioY = 435;
        ratX=50;
        ratY=0;
        try {
            background = ImageIO.read(new File("src/PizzaBox.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            mario = ImageIO.read(new File("src/ABC.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            pizza = ImageIO.read(new File("src/PieFinal.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            rat = ImageIO.read(new File("src/Rat.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            basil = ImageIO.read(new File("src/Basil.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(mario, marioX, marioY, null);
        g.drawImage(pizza, 150,200,null);
        g.drawImage(rat, ratX, ratY, null);

        // set font and color of text
        g.setFont(new Font("Arial", Font.BOLD, 16));
        if (yellowColor) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawString("Score: " + score, 50, 30);
    }

    @Override
    public void mouseClicked(MouseEvent e) { } // unimplemented
    // unimplemented because if you move your mouse while clicking, this method isn't
    // called, so mouseReleased is best

    @Override
    public void mousePressed(MouseEvent e) { } // unimplemented

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            yellowColor = !yellowColor;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { } // unimplemented

    @Override
    public void mouseExited(MouseEvent e) { } // unimplemented

    @Override
    public void keyTyped(KeyEvent e) { } // unimplemented

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {  // A key; VK_A equals 65
            if(marioX > 0)
                marioX -= 10;
            try {
                mario = ImageIO.read(new File("src/ABC.png"));
            } catch (IOException error) { }
            repaint();
        }





        if (keyCode == KeyEvent.VK_D) {  // D key; VK_D equals 65
            if(marioX < 920)
                marioX += 10;
            try {
                mario = ImageIO.read(new File("src/ABC.png"));
            } catch (IOException error) { }
            repaint();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) { }  // unimplemented
}
