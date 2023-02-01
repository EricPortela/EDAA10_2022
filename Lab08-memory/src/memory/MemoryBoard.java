package memory;
import java.util.*;


public class MemoryBoard {

	private int size;
	private Boolean[][] frontBackBoard;
	private MemoryCardImage[][] cardBoard;


	/** Skapar ett memorybräde med size * size kort. backFileName är filnamnet 
	    för filen med baksidesbilden. Vektorn frontFileNames innehåller filnamnen 
	    för frontbilderna. */
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {

		//Ge attributen relevanta startvärden
		this.size = size;
		frontBackBoard = new Boolean[size][size];
		cardBoard = new MemoryCardImage[size][size];

		populateBooleanMatrix();
		createCards(backFileName, frontFileNames);
	}

	/* Sätter in värdet false i alla positioner i frontBackBoard matrisen. Dvs korten är inte vända! */
	private void populateBooleanMatrix() {
		for (int r = 0; r<frontBackBoard.length; r++) {
			for (int c = 0; c<frontBackBoard.length; c++) {
				frontBackBoard[r][c] = false;
			}
		}
	}

	/* Skapar size * size / 2 st memorykortbilder.
	   Placerar ut varje kort på två slumpmässiga ställen på spelplanen. */
	private void createCards(String backFileName, String[] frontFileNames) {

		//Relevanta hjälpvariabler
		Random rand = new Random();

		for (String frontFileName: frontFileNames) {

			int placedCards = 0;
			MemoryCardImage card = new MemoryCardImage(backFileName, "Lab08-memory/"+frontFileName);

			while (placedCards != 2) {
				//Slumpa tal mellan 0-3 för både rad och kolumn
				int r = rand.nextInt(4);
				int c = rand.nextInt(4);

				if (cardBoard[r][c] == null) {
					cardBoard[r][c] = card; //Lägg in kortet på index (r,c)
					placedCards ++;
				}
			}
		}

		// for (MemoryCardImage[] row: cardBoard) {
		// 	for (MemoryCardImage c: row) {
		// 		System.out.println(c);
		// 	}
		// }
	}

	/** Tar reda på brädets storlek. */
	public int getSize() {
		return size;
	}
	
	/** Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c.
	    Raderna och kolonnerna numreras från 0 och uppåt. */
	public MemoryCardImage getCard(int r, int c) {
		return cardBoard[r][c];
	}

	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {
		if (frontBackBoard[r][c] == true) { 
			frontBackBoard[r][c] = false; //Baksida upp
		} else {
			frontBackBoard[r][c] = true; //Framsida upp
		}
	}
	
	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {
		return frontBackBoard[r][c];
	}
	
	/** Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2, 
	    kolonn c2. */
	public boolean same(int r1, int c1, int r2, int c2) {
		if (cardBoard[r1][c1].equals(cardBoard[r2][c2])) {
			return true;
		}
		return false;
	}

	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {
		for (Boolean[] row: frontBackBoard) {
			for (Boolean cardUp: row) {
				if (cardUp == false) {
					return false;
				}
			}
		}
		return true;
	}
}