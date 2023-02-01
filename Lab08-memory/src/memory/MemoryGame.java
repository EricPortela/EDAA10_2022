package memory;

import se.lth.cs.pt.window.SimpleWindow;

public class MemoryGame {
	public static void main(String[] args) {
		String[] frontFileNames = {"can.jpg", "flopsy_mopsy_cottontail.jpg",
				"friends.jpg", "mother_ladybird.jpg", "mr_mcgregor.jpg",
				"mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg" };
		
		// Fyll i egen kod här

		MemoryGame game = new MemoryGame();
		int tries = 0;

		//Steg 1 - Skapa board med kort
		MemoryBoard board = new MemoryBoard(4, "Lab08-memory/back.jpg", frontFileNames);

		//Steg 2 - Skapa fönstret som innehåller board:et + rita board:et
		MemoryWindow memoryWindow = new MemoryWindow(board);
		memoryWindow.drawBoard();

		while (!board.hasWon()) {
			
			memoryWindow.waitForMouseClick();

			int r1 = memoryWindow.getMouseRow();
			int c1 = memoryWindow.getMouseCol();

			while (board.frontUp(r1, c1)) {
				memoryWindow.waitForMouseClick();

				r1 = memoryWindow.getMouseRow();
				c1 = memoryWindow.getMouseCol();
			}

			board.turnCard(r1, c1);
			memoryWindow.drawCard(r1, c1);

			memoryWindow.waitForMouseClick();

			int r2 = memoryWindow.getMouseRow();
			int c2 = memoryWindow.getMouseCol();

			while (board.frontUp(r2, c2)) {
				memoryWindow.waitForMouseClick();

				r2 = memoryWindow.getMouseRow();
				c2 = memoryWindow.getMouseCol();
			}

			board.turnCard(r2, c2);
			memoryWindow.drawCard(r2, c2);

			if (!board.same(r1, c1, r2, c2)) {
				memoryWindow.delay(1000);
				board.turnCard(r1, c1);
				board.turnCard(r2, c2);
				memoryWindow.drawCard(r1, c1);
				memoryWindow.drawCard(r2, c2);
			} else {
				tries ++;
			}
			
			



			// if (game.turnCard(board, memoryWindow, r1, c1)) {

			// 	memoryWindow.waitForMouseClick();
			// 	int r2 = memoryWindow.getMouseRow();
			// 	int c2 = memoryWindow.getMouseCol();

			// 	if (game.turnCard(board, memoryWindow, r2, c2) && !board.same(r1, c1, r2, c2)) {
			// 		memoryWindow.delay(1000);
			// 		board.turnCard(r1, c1);
			// 		board.turnCard(r2, c2);
			// 		memoryWindow.drawCard(r1, c1);
			// 		memoryWindow.drawCard(r2, c2);

			// 		tries ++;
			// 	}
			// }
		}
		
		System.out.println("Du vann! Antal försök: " + tries);
	}


	private Boolean turnCard(MemoryBoard b, MemoryWindow w, int r, int c) {
		if (!b.frontUp(r, c)) {
			b.turnCard(r, c);
			w.drawCard(r, c);
			return true;
		}
		return false;
	}
}