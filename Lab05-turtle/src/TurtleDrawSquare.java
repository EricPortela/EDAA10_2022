import se.lth.cs.pt.window.SimpleWindow;

public class TurtleDrawSquare {
 	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		Turtle t = new Turtle(w, 300, 300);
		t.penDown();

		for (int i = 0; i < 4; i++) {
			t.forward(100);
			t.left(90);
			System.out.println(t.getX());
			System.out.println(t.getY());
		}
	}
}
