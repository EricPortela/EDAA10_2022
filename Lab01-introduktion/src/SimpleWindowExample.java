import java.awt.Color;

import se.lth.cs.pt.window.SimpleWindow;

public class SimpleWindowExample {

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Drawing Window");
        w.moveTo(100, 100); //Moves the pen to
        // w.lineTo(150, 100); //Draws line 

		w.setLineColor(Color.RED);
		
		w.writeText("TEST");
        
        int xStart = 100;
        int yStart = 100;
        
		xStart += 50;
        w.lineTo(xStart, yStart);

		yStart -= 50;
		w.lineTo(xStart, yStart);

		xStart -= 50;
        w.lineTo(xStart, yStart);

		yStart += 50;
        w.lineTo(xStart, yStart);


	}

}
