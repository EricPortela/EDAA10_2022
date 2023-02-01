import java.util.Random;


public class MoleTurtle extends RaceTurtle {

    public MoleTurtle(RaceWindow w, int nbr) {
        super(w, nbr); //Kalla på RaceTurtles konstruktor
        this.nbr = nbr;
    }

    @Override
    public void raceStep() {
        // TODO Auto-generated method stub
        Random rand = new Random();

        if (rand.nextBoolean()) {
            penUp();
        } else {
            penDown();
        }
        super.raceStep();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Nummer " + nbr + " - MoleTurtle";

    }
    
}
