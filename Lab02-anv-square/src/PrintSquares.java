import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class PrintSquares {

    //PUNKT 7
    public static void main(String[] args) {

        SimpleWindow w = new SimpleWindow(500, 500, "PrintSquares");
        Square sq = new Square(0, 0, 100);

        while (true) {
			w.waitForMouseClick();
            sq.erase(w);
			sq = new Square(w.getClickedX(), w.getClickedY(), 100);
		    sq.draw(w);
		}
    }
}
