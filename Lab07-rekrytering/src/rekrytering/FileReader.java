package rekrytering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Mikael Brännström

public class FileReader {

	/**
	 * Returnerar max nbrOfRows rader från filen som en vektor av Applicant-objekt.
	 * Läser i filen tills det inte finns fler rader eller tills man läst nbrOfRows
	 * rader (det som inträffar först). 
	 * Returnerar null om filen inte finns.
	 */
	public static Applicant[] readFromFile(String fileName, int nbrOfRows) {
		Scanner scan;
		try {
			scan = new Scanner(new File(fileName), "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("File not found" + fileName);
			e.printStackTrace();
			return null;
		}

		//Här kan du använda Scannern för att läsa från filen fileName.
		//Varje rad motsvarar en Applicant. Kontrollera vad Applicants konstruktor kräver
		//Alla Applicant-objekt (max nbrOfRows stycken) ska lagras i en Applicant-vektor och returneras på slutet


		Applicant[] applicantArray = new Applicant[nbrOfRows];
		int count = 0;

		while (scan.hasNextLine() && nbrOfRows > 0) {
				String[] dataLine = scan.nextLine().split(" ");
				
				if (dataLine.length > 1) {
					String name = dataLine[0] + " " + dataLine[1];
					String grades = dataLine[2];
		
					// System.out.println(name);
					// System.out.println(grades);
		
					Applicant applicant = new Applicant(name, grades);
		
					applicantArray[count] = applicant;
		
					count ++;
					nbrOfRows --;
				}
			
		}

		// System.out.println(count);
		return applicantArray; //Byt ut denna rad mot hela lösningen
	}
}
