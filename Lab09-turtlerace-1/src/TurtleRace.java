import java.util.ArrayList;
import java.util.Random;

public class TurtleRace {
    public static void main(String[] args) {

        //Lab del 1
        //raceNormalTurtles();

        //Lab del 2
        raceSpecialTurtles();  
    }

    private static void raceNormalTurtles() {
        ArrayList<RaceTurtle> turtles = new ArrayList();
    
        RaceWindow w = new RaceWindow();
    
        for (int i = 1; i<9; i++) {
            RaceTurtle t = new RaceTurtle(w, i);
            turtles.add(t);
        }
    
        int inGoal = 0;
    
        while (turtles.size() != 0) {
            for (int i = 0; i<turtles.size(); i++) {
                RaceTurtle r = turtles.get(i);
    
                r.raceStep();
                RaceWindow.delay(5);
    
                if (r.getX() >= RaceWindow.X_END_POS) {
                    turtles.remove(i);
                    
                    if (inGoal < 3) {
                        System.out.println("På plats " + (inGoal+1) + ": "+ r.toString());
                        inGoal ++;
                    }
                }
            }
        }  
    }


    private static void raceSpecialTurtles() {

        ArrayList<RaceTurtle> raceTurtles = new ArrayList();

        RaceWindow w = new RaceWindow();

        for (int i = 1; i<9; i++) {
            Random rand = new Random();
            int randNbr = rand.nextInt(3);

            if (randNbr == 0) { //AbsenMindTurtle
                raceTurtles.add(new AbsentMindTurtle(w, i, rand.nextInt(100)));
            } else if (randNbr == 1) { //DizzyTurtle
                raceTurtles.add(new DizzyTurtle(w, i, rand.nextInt(6)+1));
            } else { //MoleTurtle
                raceTurtles.add(new MoleTurtle(w, i));
            }

            System.out.println(raceTurtles.get(i-1).toString());
        }

        int inGoal = 0;
    
        while (raceTurtles.size() != 0) {
            for (int i = 0; i<raceTurtles.size(); i++) {
                RaceTurtle r = raceTurtles.get(i);
    
                r.raceStep();
                RaceWindow.delay(5);
    
                if (r.getX() >= RaceWindow.X_END_POS) {
                    raceTurtles.remove(i);

                    if (inGoal < 3) {
                        System.out.println("På plats " + (inGoal+1) + ": "+ r.toString());
                        inGoal ++;
                    }
                }
            }
        }

    }
}