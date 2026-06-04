import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener, ActionListener {
    private double time;
    private boolean yellowColor;
    private int sliceX;
    private int sliceY;
    private int ratX;
    private int ratY;
    private BufferedImage background;
    private BufferedImage pizza;
    private BufferedImage slice;
    private BufferedImage rat;
    private BufferedImage basil;
    private Timer t;
    private int speed;



    public DisplayPanel() {
        time = 0;
        yellowColor = true;
        sliceX = 230;
        sliceY = 435;
        ratX=50;
        ratY=0;
        speed = 2;
        t = new Timer(10, this);
        try {
            background = ImageIO.read(new File("src/PizzaBox.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            slice = ImageIO.read(new File("src/ABC.png"));
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
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(slice, sliceX, sliceY, null);
        g.drawImage(pizza, 150,200,null);
        g.drawImage(rat, ratX, ratY, null);

        // set font and color of text
        g.setFont(new Font("Arial", Font.BOLD, 16));
        if (yellowColor) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawString("Time: " + ((int) (time)), 10, 30);
        if(time < 6){
            g.drawString("DONT LET ANY RATS STEAL YOUR PIZZA! " , 100, 30);
        }
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

    public void moveRat(MouseEvent e){
        ratY -= 3;
        if(ratY > 540){
            ratY = 0;
            ratX = (int) (Math.random() *540);
        }
        repaint();
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
            if(sliceX > 0)
               sliceX -= 20;
            try {
                slice = ImageIO.read(new File("src/ABC.png"));
            } catch (IOException error) { }
            repaint();
        }





        if (keyCode == KeyEvent.VK_D) {  // D key; VK_D equals 65
            if(sliceX < 480)
                sliceX += 20;
            try {
                slice = ImageIO.read(new File("src/ABC.png"));
            } catch (IOException error) { }
            repaint();
        }
    }
    public void moveRat(){
        ratY += speed;
        if(ratY >= 540){
            ratY=0;
            ratX = (int) (Math.random() * 460) + 40;
        }
        repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) { }  // unimplemented

    @Override
    public void actionPerformed(ActionEvent e) {
        moveRat();
        time+= 0.02/1.5;
        speed = (int) (time/20 + 1.0);
    }
}
