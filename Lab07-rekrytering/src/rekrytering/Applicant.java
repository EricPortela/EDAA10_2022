package rekrytering;

public class Applicant implements Comparable<Applicant> {
	//Varje sökande har ett namn och ett antal betyg
	private String name;
	private int[] grades;

	public Applicant(String name, String gradesAsString) {
		this.name = name;
		// Har flyttat tolkningen av betygen till en privat hjälpmetod för att hålla
		// konstruktorn kortare
		// Anropa denna och skicka vidare parametern som innehåller betygen
		parseGrades(gradesAsString);
	}

	private void parseGrades(String gradesAsString) {
		// gradesAsString har formatet x,y,z,q där respektive bokstav är ett betyg
		// Om vi splittar strängen på komma (",") hamnar varje betyg i en vektor
		String[] g = gradesAsString.split(",");
		// Skapa vektorn med heltal
		grades = new int[g.length];
		// Iterera över alla betyg för att översätta dessa till ett heltal
		for (int i = 0; i < g.length; i++) {
			try {
				if (g[i].equals("U") || g[i].equals("-1")) { //FRÅGA 4
					// Om underkänd så räknar vi det som en nolla
					grades[i] = 0;
				} else {
					int grade = Integer.parseInt(g[i]);

					if (grade >= 0 && grade <=5) {
						grades[i] = Integer.parseInt(g[i]);
					} else {
						grades[i] = 0;
					}
				}
			} catch(NumberFormatException ex) {
				if (g[i].equals("A")) {
					grades[i] = 5;
				} else if (g[i].equals("B")) {
					grades[i] = 4;
				} else if (g[i].equals("C")) {
					grades[i] = 3;
				} else if (g[i].equals("D")) {
					grades[i] = 2;
				} else if (g[i].equals("E")) {
					grades[i] = 1;
				} else {
					grades[i] = 0;
				}
			}
		}
	}

	public double getAvgGrade() {
		int sum = 0; 
		//Lösning 1: For-loop
		// for (int i = 0; i<grades.length; i++) {
		// 	sum += grades[i];
		// }

		//Lösning 2: Går även lika bra med en for-each loop

		
		for (int grade: grades) {
			if (grade == -1) {
				sum += 0;
			} else {
				sum += grade;
			}
		}
		
		return sum/grades.length; 
	}
	
	//Implementera denna när labbeskrivningen kräver det 
	public String toString() {
		double avg = 0;

		for (int grade: grades) {
			avg += grade;
		}

		avg /= grades.length;

		return name + grades + "(avg: " + avg + ")" ;
	}
	 

	/*
	 * Metod för att jämföra detta Applicant-objekt med ett annat och få ut vilket
	 * som är störst. Retunerar något > 0 om detta objektet är störst. Returnerar något < 0 om other är störst och returnerar 0 om objekten är lika.
	 * Används av javas inbyggda sorteringsmetoder
	 */
	public int compareTo(Applicant other) {
		// Om exakt samma objekt
		if (other == this) {
			return 0;
		}
		// Annars jämför snittbetyget i första hand
		int gradeRes = (int) Math.round((getAvgGrade() - ((Applicant) other).getAvgGrade()) * 100);
		if (gradeRes == 0) {
			// Om snittbetyget är samma, låt namnet avgöra på namnet
			return name.compareTo(((Applicant) other).name);
		}
		return gradeRes;
	}
}
