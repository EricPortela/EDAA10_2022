import java.util.Random;

public class AbsentMindTurtle extends RaceTurtle {

    private int crazyness;

    public AbsentMindTurtle(RaceWindow w, int nbr, int crazyness) {
        super(w, nbr); //Kalla på RaceTurtles konstruktor
        this.nbr = nbr;
        this.crazyness = crazyness;
    }

    @Override
    public void raceStep() {
        // TODO Auto-generated method stub

        Random rand = new Random();

        if (rand.nextInt(100) >= crazyness) {
            super.raceStep();
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return "Nummer " + nbr + " - AbsentMindTurtle" + " ("+ crazyness + "% Frånvarande)";
    }
}
