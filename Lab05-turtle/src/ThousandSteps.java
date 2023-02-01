import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class ThousandSteps {

    public static void main(String[] args) {
        
        SimpleWindow w = new SimpleWindow(500, 500, "Thousand Steps");
        Random rand = new Random();

        int xHalf = w.getWidth()/2;
        int yHalf = w.getHeight()/2;

        Turtle t = new Turtle(w, xHalf, yHalf); //Skapa Turtle som börjar vandring i mitten av SimpleWindow
        t.penDown();

        int steps = 0;
        int degree = 0;

        for (int i = 0; i<1000; i++) {
            steps = rand.nextInt(10) + 1;
            degree = rand.nextInt(360) - 180; //Slumpar tal mellan -180-180

            t.forward(steps);
            t.left(degree);
        }
    }   
}
