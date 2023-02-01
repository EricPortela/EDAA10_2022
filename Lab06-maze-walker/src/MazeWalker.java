
import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.maze.*;

public class MazeWalker {
    private Turtle turtle;

    public MazeWalker(Turtle turtle) {
        this.turtle = turtle;
        turtle.turnNorth();
    }

    /** Låter sköldpaddan vandra genom labyrinten maze, från
        ingången till utgången. */
    public void walk(Maze maze) {

        while (! maze.atExit(turtle.getX(), turtle.getY())) {
			if (! maze.wallAtLeft(turtle.getDirection(), turtle.getX(), turtle.getY())) {
				// an opening on the left, let's go there
				turtle.left(90);
				turtle.forward(1);
			} else if (! maze.wallInFront(turtle.getDirection(), turtle.getX(), turtle.getY())) {
				turtle.forward(1);
			} else {
				turtle.left(-90);
			}
			SimpleWindow.delay(1);
		}
    } 
}