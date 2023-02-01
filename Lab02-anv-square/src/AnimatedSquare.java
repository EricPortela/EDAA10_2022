import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.*;

public class AnimatedSquare {

    //PUNKT 9

    public static void main(String[] args) {
        SimpleWindow w = new SimpleWindow(500, 500, "PrintSquares");
        Square sq = new Square(0, 0, 100);
        
        int oldX = 0;
        int oldY = 0;
        int k = 0;
        int m = 0;
        int xLenght = 0;

        int x = 0;
        int y = 0;
        int addX = 0;

        int deltaY = 0;
        int deltaX = 0;

        while (true) {
			w.waitForMouseClick();

            System.out.println("CLICKED!");

            //Calculate y=kx+m
            if (oldX-w.getClickedX() != 0) {
                deltaY = oldY-w.getClickedY();
                deltaX = oldX - w.getClickedX();
                if (deltaY == 0) {
                    k = 0;
                }

                k = (deltaY)/(deltaX);

                m = w.getClickedY() - (k*w.getClickedX());

                xLenght = (w.getClickedX()-oldX);
    
                if (Math.abs(xLenght) > 10) {
                    x = oldX;
                    addX = xLenght/10;
                    for (int i = 0; i<10; i++) {
                        x = oldX + (addX*i);
                        y = (k*x)+m;
                        if (x > 0 && y > 0) {
                            sq = new Square(x, y, 50);
                            
                            //ANIMATION CODE
                            sq.draw(w);
                            SimpleWindow.delay(100);
                            sq.erase(w);
                        }
                    }
                }
    
                oldX = x;
                oldY = y;
            }
		}
    }
    
}
