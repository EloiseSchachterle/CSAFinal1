public class Mushroom {

        private int mX;
        private int mY;
        private int speed;
        public Mushroom(int x, int y, int speed){
            mX = x;
            mY = y;
            this.speed = speed;
        }

        public int getX(){
            return mX;
        }

        public int getY(){
            return mY;
        }


        public void move(){
            mY += speed;
        }


}
