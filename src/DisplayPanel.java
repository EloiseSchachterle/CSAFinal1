import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.MouseInfo;
import java.awt.Point;

public class DisplayPanel extends JPanel implements MouseListener, KeyListener, ActionListener {
    private double time;
    private int sliceX;
    private int sliceY;
    private int ratX;
    private int ratY;
    private BufferedImage background;
    private BufferedImage pizza;
    private BufferedImage slice;
    private BufferedImage rat;
    private BufferedImage basil;
    private BufferedImage won;
    private BufferedImage lostPhoto;
    private BufferedImage boom;
    private BufferedImage reset;
    private Timer t;
    private int speed;
    private boolean lost;
    private boolean showBoom;
    private int boomX;
    private int boomY;
    private double boomEndTime;




    public DisplayPanel() {
        lost = false;
        time = 0;
        sliceX = 230;
        sliceY = 435;
        ratX=235;
        ratY=0;
        speed = 2;
        t = new Timer(10, this);

        try {
            won = ImageIO.read(new File("src/FinalPizzaWon.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            reset = ImageIO.read(new File("src/ResetButton.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            boom = ImageIO.read(new File("src/boom.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            lostPhoto = ImageIO.read(new File("src/RatWon.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
        setFocusable(true);
        requestFocusInWindow();
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(time <= 60) {
            g.drawImage(background, 0, 0, null);
            g.drawImage(slice, sliceX, sliceY, null);
            g.drawImage(pizza, 150, 200, null);
            g.drawImage(rat, ratX, ratY, null);
        }
        else{
            g.drawImage(won, 0,0, null);
            g.drawImage(reset, 400,400, null);
        }


        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);

        g.drawString("Time: " + ((int) (time)), 10, 30);
        if(time < 6){
            g.drawString("DONT LET ANY RATS STEAL YOUR PIZZA! " , 100, 30);
        }
        if(time > 60){
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("YOU WIN!!! " , 125, 250);

        }
        if(lost){
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawImage(lostPhoto, 0, 0, null);
            g.drawString("YOU LOOSE!!", 125, 250);
            g.drawImage(reset, 400,400, null);
            t.stop();
        }
        if(showBoom && !lost && time <= 60)
            g.drawImage(boom, boomX, boomY, null);


    }

    @Override
    public void mouseClicked(MouseEvent e) { }


    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if((mouseX > 400 && mouseX < 550) &&
                (mouseY > 400 && mouseY < 550) &&
                (lost || time > 60)){
            time = 0;
            lost = false;
            sliceX = 230;
            ratX=235;
            ratY=0;
            speed = 2;
            t.start();
        }

    }
    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            if(sliceX > 0)
                sliceX -= 20;
            try {
                slice = ImageIO.read(new File("src/ABC.png"));
            } catch (IOException error) { }
            repaint();
        }





        if (keyCode == KeyEvent.VK_D) {
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

        repaint();
    }
    private Rectangle sliceRectangle() {
        int imageHeight = slice.getHeight();
        int imageWidth = slice.getWidth();
        Rectangle rect = new Rectangle(sliceX, sliceY, imageWidth, imageHeight);
        return rect;
    }

    private Rectangle ratRectangle() {
        int imageHeight = rat.getHeight();
        int imageWidth = rat.getWidth();
        Rectangle rect = new Rectangle(ratX, ratY, imageWidth, imageHeight);
        return rect;
    }



    @Override
    public void keyReleased(KeyEvent e) { }  // unimplemented

    @Override
    public void actionPerformed(ActionEvent e) {
        moveRat();
        time+= 0.02/1.5;
        speed = (int) (time/20 + 1.0);
        if(sliceRectangle().intersects(ratRectangle())){
            showBoom = true;
            boomX = ratX;
            boomY = ratY;
            boomEndTime = time + 1;
            ratY = -30;
            ratX = (int) (Math.random() * 460) + 40;

        }
        if(showBoom && (time > boomEndTime))
            showBoom = false;
        if (ratY > 540)
            lost = true;
        if(time > 60)
            t.stop();



    }

}
