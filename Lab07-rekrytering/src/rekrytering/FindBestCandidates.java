package rekrytering;
import java.util.*;

public class FindBestCandidates {
	private static final double MIN_AVG_GRADE = 4.0;

	public static void main(String[] args) {

		//--------- STEG 1 ---------

		//--------- BESKRIVNING ---------
		//Läser in fil och lägger in varje rad som element i en array, vilken returneras

		//METOD: readFromFile(fileName, nbrOfRows)
		//PARAM: filnamn + max antal rader som ska läsas in
		//OUTPUT: Vektor som innehåller alla ansökningars information
		//1 element = namn efternamn [betyg](avg: nbr)
		Applicant[] test = FileReader.readFromFile("Lab07-rekrytering/applications_x.txt", 20);


		//--------- STEG 2 ---------

		//--------- BESKRIVNING ---------
		//Hitar de bästa kandiaterna, baserat på medelbetyg

		//METOD: findBestCandidates(Applicants[])
		//PARAM: Array med ansökningarnas information (det som ges ut i STEG 1)
		//OUTPUT: Printar ut varje element i Array:en med 
		for (Applicant a : FindBestCandidates.findBestCandidates(test)) {
			System.out.println(a);
		}


		// Läs från fil (Börja med "applications_small.txt), spara resultatet i en
		// vektor

		// Skicka in Applicant-vektorn (som du fick i föregående steg) till metoden
		// findBestCandidiates (nedan)
		// Spara resultatet i en vektor

		// Printa resultatet från findBestCandidates
	}

	// Hitta alla kandidater som har medelbetyg över MIN_AVG_GRADE
	// Lagra alla dessa kandidater i en vektor, returnera vektorn
	public static Applicant[] findBestCandidates(Applicant[] applicants) {
		
		//Hitta den rätta längden för array
		//Annars erhålls error om man har element som är null vid Array.sort()
		int length = 0;
		for (Applicant a: applicants) {
			if (a != null && a.getAvgGrade() >= MIN_AVG_GRADE) {
				length ++;
			}
		}

		//Populate:a array
		Applicant[] bestCandidates = new Applicant[length];
		int count = 0;

		for (Applicant a: applicants) {
			if (a != null && a.getAvgGrade() >= MIN_AVG_GRADE) {
				bestCandidates[count] = a;
				count ++;
			} 
		}

		Arrays.sort(bestCandidates); 
		return bestCandidates;
	}

}
