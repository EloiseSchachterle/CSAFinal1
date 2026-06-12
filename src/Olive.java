public class Olive {
    private int oX;
    private int oY;
    private int speed;
    public Olive(int x, int y, int speed){
        oX = x;
        oY = y;
        this.speed = speed;
    }

    public int getX(){
        return oX;
    }

    public int getY(){
        return oY;
    }


    public void move(){
        oY += speed;
    }

}
