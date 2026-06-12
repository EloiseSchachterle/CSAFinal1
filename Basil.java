public class Basil {
    private int basilX;
    private int basilY;
    private int speed;
    public Basil(int x, int y, int speed){
        basilX = x;
        basilY = y;
        this.speed = speed;
    }

    public int getX(){
        return basilX;
    }

    public int getY(){
        return basilY;
    }


    public void move(){
        basilY += speed;
    }
}
