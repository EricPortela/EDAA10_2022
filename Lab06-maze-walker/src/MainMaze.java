import se.lth.cs.pt.maze.*;
import se.lth.cs.pt.window.*;


public class MainMaze {

    public static void main(String[] args) {

        SimpleWindow s = new SimpleWindow(500, 500, "Maze");

        Turtle t = new Turtle(s, 0, 0);

        MazeWalker mw = new MazeWalker(t);

        
    }
    
}
