public class Pepperoni {
    private int pepperoniX;
    private int pepperoniY;
    private int speed;
    public Pepperoni(int x, int y, int speed){
        pepperoniX = x;
        pepperoniY = y;
        this.speed = speed;
    }

    public int getX(){
        return pepperoniX;
    }

    public int getY(){
        return pepperoniY;
    }



    public void setX(int x){
        pepperoniX = x;
    }

    public void move(){
        pepperoniY += speed;
    }
}
