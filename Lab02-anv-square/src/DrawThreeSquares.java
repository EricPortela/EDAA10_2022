import se.lth.cs.pt.window.SimpleWindow;

// import java.nio.file.WatchEvent;

import se.lth.cs.pt.square.Square;

public class DrawThreeSquares {

    //Punkt 4

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");

        int xStart = 250;
        int yStart = 250;
        
		// Square sq;
        Square sq = null; //Kommentera bort (STEG 5)


        for (int i = 0; i<3; i++) {
            sq = new Square(xStart, yStart, 100); //Kommentera bort (STEG 5)
            sq.draw(w); //null (STEG 5)
            if (i%2 == 0) {
                System.out.println("First");
                xStart += 50;
                yStart -= 30;
            } 
            else {
                System.out.println("Second");
                xStart -= 20;
                yStart += 60;
            }
        }
	}
}
