import java.util.Random;

public class DizzyTurtle extends RaceTurtle {

    private int dizzyness;
    private int rightPathY;

    public DizzyTurtle(RaceWindow w, int nbr, int dizzyness) {
        super(w, nbr); //Kalla på RaceTurtles konstruktor
        this.dizzyness = dizzyness;
        this.rightPathY = getY();
        this.nbr = nbr;
    }

    @Override
    public void raceStep() {
        // TODO Auto-generated method stub

        if (getY() < rightPathY + dizzyness) {
            left(-1*dizzyness);
        } else {
            left(dizzyness);
        }

        super.raceStep();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Nummer " + nbr + " - DizzyTurtle" + " (Yrsel: " + dizzyness + ")";
    }
    
}
