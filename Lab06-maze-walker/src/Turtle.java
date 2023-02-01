import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class Turtle {

	private SimpleWindow w;
	private double x;
	private double y;
	private double alfa; //in radianns and double!! ==> Viktigt att den är double ==> Avrunda ALLTID sist!
	private Boolean isPenDown;

	/** Skapar en sköldpadda som ritar i ritfönstret w. Från början 
	    befinner sig sköldpaddan i punkten x, y med pennan lyft och 
	    huvudet pekande rakt uppåt i fönstret (i negativ y-riktning). */

	public Turtle(SimpleWindow w, int x, int y) {
		alfa = Math.toRadians(90);
		isPenDown = false;

		this.w = w;
		this.x = x;
		this.y = y;

		w.moveTo(x, y); //Mycket VIKTIGT att flytta pennan till x,y i SimpleWindow-fönstret!!!
	}

	/** Sänker pennan. */
	public void penDown() {
		isPenDown = true;
	}
	
	/** Lyfter pennan. */
	public void penUp() {
		isPenDown = false;
	}
	
	/** Går rakt framåt n pixlar i den riktning huvudet pekar. */
	public void forward(int n) {

		//Mycket VIKTIGT att flytta pennan till x,y i SimpleWindow-fönstret!!!
		//T.ex. om man skulle göra två stycken objekt av turtle och lägga in samma SimpleWindow
		//Se testTwoTurtles i TurtleTest!
		w.moveTo((int)x, (int)y); 
		
		x += (n*(Math.cos(alfa)));
		y -= (n*(Math.sin(alfa)));

		if (isPenDown) {
			w.lineTo((int) Math.round(x), (int) Math.round(y));
		} 
	}

	/** Vrider beta grader åt vänster runt pennan. */
	public void left(int beta) {
		alfa += Math.toRadians(beta);
	}

	/** Går till punkten newX, newY utan att rita. Pennans läge (sänkt
	    eller lyft) och huvudets riktning påverkas inte. */
	public void jumpTo(int newX, int newY) {
		w.moveTo(newX, newY);
		x = newX;
		y = newY;
	}

	/** Återställer huvudriktningen till den ursprungliga. */
	public void turnNorth() {
		alfa = Math.toRadians(90);
	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position. */
	public int getX() {
		return (int) Math.round(x);
	}

 	/** Tar reda på y-koordinaten för sköldpaddans aktuella position. */
	public int getY() {
		return (int) Math.round(y);
	}
  
	/** Tar reda på sköldpaddans riktning, i grader från den positiva X-axeln. */
 	public int getDirection() {
 		return (int) Math.toDegrees(alfa);
	}
}
