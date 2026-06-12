import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DisplayPanel extends JPanel implements MouseListener, KeyListener, ActionListener {
    private double time = 0;
    private int sliceX = 230;
    private int sliceY = 435;
    private int ratX = 235;
    private int ratY = 0;
    private BufferedImage background;
    private BufferedImage pizza;
    private BufferedImage slice;
    private BufferedImage rat;
    private BufferedImage basil1;
    private BufferedImage olive1;
    private BufferedImage mushroom1;
    private BufferedImage won;
    private BufferedImage lostPhoto;
    private BufferedImage boom;
    private BufferedImage reset;
    private Timer t = new Timer(10, this);
    private int speed = 2;
    private boolean lost = false;
    private boolean showBoom;
    private int boomX;
    private int boomY;
    private double boomEndTime;
    private BufferedImage pepperoniImage;
    private ArrayList<Pepperoni> pepperonis = new ArrayList<>();
    private ArrayList<Olive> olives = new ArrayList<>();
    private ArrayList<Basil> basils = new ArrayList<>();
    private ArrayList<Mushroom> mushrooms = new ArrayList<>();



    public DisplayPanel() {
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
            background = ImageIO.read(new File("src/new.jpeg"));
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
            basil1 = ImageIO.read(new File("src/Basil2.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            olive1 = ImageIO.read(new File("src/Olive1.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            mushroom1 = ImageIO.read(new File("src/mushroom.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            pepperoniImage = ImageIO.read(new File("src/Pepperoni2.png"));
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
            g.drawImage(pizza, 125, 125, null);
            g.drawImage(rat, ratX, ratY, null);
            for(Pepperoni p : pepperonis){
                g.drawImage(pepperoniImage, p.getX(), p.getY(), null);
            }

            for(Basil p : basils){
                g.drawImage(basil1, p.getX(), p.getY(), null);
            }

            for(Olive p : olives){
                g.drawImage(olive1, p.getX(), p.getY(), null);
            }

            for(Mushroom p : mushrooms){
                g.drawImage(mushroom1, p.getX(), p.getY(), null);
            }
        }
        else{
            g.drawImage(won, 0,0, null);
            g.drawImage(reset, 400,400, null);
        }


        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);

        g.drawString("Time: " + ((int) (time)), 10, 30);
        if(time < 60){
            g.drawString("DONT LET ANY RATS STEAL YOUR PIZZA! " , 100, 30);
        }
        if(time > 60){
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("YOU WIN!!! " , 75, 400);

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
        if(Math.random() < 0.02){
            pepperonis.add(new Pepperoni((int)(Math.random()*500), -30, (int)(Math.random() *3)+2));
        }

        for(int i = pepperonis.size()-1; i >= 0; i--){
            pepperonis.get(i).move();
            if(pepperonis.get(i).getY() > 540){
                pepperonis.remove(i);
            }
        }

        if(Math.random() < 0.02){
            basils.add(new Basil((int)(Math.random()*500), -30, (int)(Math.random() *3)+2));
        }

        for(int i = basils.size()-1; i >= 0; i--){
            basils.get(i).move();
            if(basils.get(i).getY() > 540){
                basils.remove(i);
            }
        }

        if(Math.random() < 0.02){
            olives.add(new Olive((int)(Math.random()*500), -30, (int)(Math.random() *3)+2));
        }

        for(int i = olives.size()-1; i >= 0; i--){
            olives.get(i).move();
            if(olives.get(i).getY() > 540){
                olives.remove(i);
            }
        }

        if(Math.random() < 0.02){
            mushrooms.add(new Mushroom((int)(Math.random()*500), -30, (int)(Math.random() *3)+2));
        }

        for(int i = mushrooms.size()-1; i >= 0; i--){
            mushrooms.get(i).move();
            if(mushrooms.get(i).getY() > 540){
                mushrooms.remove(i);
            }
        }

    }

}
