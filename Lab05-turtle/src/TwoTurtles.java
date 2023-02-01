import se.lth.cs.pt.window.SimpleWindow;
import java.util.*;

public class TwoTurtles {

    public static void main(String[] args) {

        SimpleWindow w = new SimpleWindow(500, 500, "Two Turtles");

        Turtle t1 = new Turtle(w, 250, 250);
        Turtle t2 = new Turtle(w, 350, 350);

        t1.penDown();
        t2.penDown();

        double dist = TwoTurtles.getDistance(t1, t2);

        while (dist >= 50) {

            //Turtle 1
            t1.forward(TwoTurtles.getRandomSteps());
            t1.left(TwoTurtles.getRandomAngle());

            //Turtle 2
            t2.forward(TwoTurtles.getRandomSteps());
            t2.left(TwoTurtles.getRandomAngle());

            dist = TwoTurtles.getDistance(t1, t2); //Uppdatera distance 
            
            SimpleWindow.delay(10);
        }
    }

    public static double getDistance(Turtle t1, Turtle t2) {
        double deltaX = Math.abs(t1.getX()-t2.getX());
        double deltaY = Math.abs(t1.getY()-t2.getY());
    
        double term1 = Math.pow(deltaX, 2);
        double term2 = Math.pow(deltaY, 2);
    
        double dist = Math.sqrt(term1 + term2);

        return dist;
    }

    public static int getRandomSteps() {
        Random rand = new Random();
        int steps = rand.nextInt(10) + 1; //Slumpar tal mellan 1-10
        return (steps);
    }

    public static int getRandomAngle() {
        Random rand = new Random();
        int degree = rand.nextInt(360) - 180; //Slumpar tal mellan -180-180
        return degree;
    }
    
}



