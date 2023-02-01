
public class Mole {

	private Graphics g = new Graphics(30, 50, 10); //width, height, blocksize

    public static void main(String[] args) {

		Mole m = new Mole();

		m.drawWorld();
		m.dig();

		// m.g.block(10, 2, Colors.MOLE);

		// m.g.rectangle(0, 0, 30, 20, Colors.GRASS);
    }

	public void drawWorld() {
		
		//Default width
		int worldWidth = g.getWidth();

		//SKY
		int heightSky = 13;
		g.rectangle(0, 0, worldWidth, heightSky, Colors.SKY);

		//CLOUDS
		g.rectangle(5, 4, 6, 2, java.awt.Color.white);
		g.rectangle(7, 6, 5, 3, java.awt.Color.white);
		g.rectangle(20, 5, 5, 3, java.awt.Color.white);

		//GRASS
		int grassHeight = 2;
		g.rectangle(0, heightSky, worldWidth, grassHeight, Colors.GRASS);

		//DIRT
		int yStart = 15;
		int worldHeight = g.getHeight()-yStart;
		g.rectangle(0, yStart, worldWidth, worldHeight, Colors.SOIL);
	} 	

	public void dig() {
		int x = g.getWidth() / 2;    // För att börja på mitten
        int y = g.getHeight() / 2;

		
        while (true) {
			
			g.block(x, y, Colors.MOLE);
			
			char key = g.waitForKey();

			if (y >= 15) {
				g.block(x, y, Colors.TUNNEL);
			} else {
				g.block(x, y, Colors.GRASS);
			}

			if ((key == 'w' || key == 'W') && y > 13) {
				y --;
			} else if ((key == 'a' || key == 'A') && x > 0) { 
				x --;
			} else if ((key == 's' || key == 'S') && y < g.getHeight() - 1) { 
				y ++;
			} else if ((key == 'd' || key == 'D') && x < g.getWidth() - 1) { 
				x ++;
			}
		}
	}
}


