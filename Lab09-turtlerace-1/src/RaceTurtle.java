import java.util.Random;


public class RaceTurtle extends Turtle {

    protected int nbr;

     /**
    * Skapar en sköldpadda som ska springa i fönstret w och som har start-
    * nummer nbr. Sköldpaddan startar med pennan nere och nosen vänd åt höger.
    */
    public RaceTurtle(RaceWindow w, int nbr) {
        super(w, w.getStartXPos(nbr), w.getStartYPos(nbr));

        this.penDown();
        this.left(-90);
        this.nbr = nbr;
    }

    /**
    * Låter sköldpaddan gå framåt ett steg. Stegets längd ges av ett
    * slumptal (heltal) mellan 1 och 6.
    */
    public void raceStep() {
        Random rand = new Random();
        int randNbr = rand.nextInt(6) + 1;

        this.forward(randNbr);
    }

    /**
    * Returnerar en läsbar representation av denna RaceTurtle,
    * på formen "Nummer x" där x är sköldpaddans startnummer.
    */
    public String toString() {

        return "Nummer " + nbr;
    }
    
}
