package se.lth.cs.pt.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Hanterar en Sprite som kan ritas, flyttas och roteras i SimpleWindow.
 */
public class Sprite {
	/* package */ final JLabel label;
	private final int side;  // bitmapens bredd == höjd för att kunna rotera fritt
	private final int width, height;
	private volatile double direction = 0;  //grader, inital rikting uppåt

	/**
	 * Skapar en ikon från en bild specificerad i filepath av angiven bredd och
	 * höjd.
	 * 
	 * @param filePath
	 * @param width
	 * @param height
	 */

	@SuppressWarnings("serial")
	public Sprite(String filePath, int width, int height) {
		try {
			side = Math.max(width, height);
			this.width = width;
			this.height = height;
	
			final Map<Key, Object> hints = new HashMap<>();
			hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			Image img = ImageIO.read(new File(filePath));
			BufferedImage padded = new BufferedImage(side, side, BufferedImage.TYPE_INT_ARGB);
			Graphics2D imgGraphics = padded.createGraphics();
			imgGraphics.addRenderingHints(hints);
			imgGraphics.drawImage(img, (side - width) / 2, (side - height) / 2, width, height, null);
			imgGraphics.dispose();
			label = new JLabel(new ImageIcon(padded)) {
				@Override
				public void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.addRenderingHints(hints);
					g2d.rotate(-Math.toRadians(direction), side / 2f, side / 2f);
					super.paintComponent(g2d);
					g2d.dispose();
				}
			};
			label.setSize(side, side);

		} catch (IOException e) {
			throw new Error(e);
		}
	}

	/**
	 * Roterar spriten till ett absolut vinkelvärde.  
	 * @param deg    vinkeln i grader moturs från den positiva x-axeln.
	 */
	public void setDirection(double deg) {		
		direction = deg;
		label.repaint();
	}
	
	/**
	 * Returnerar riktningen i grader moturs från positiva x-axeln.
	 */
	public double getDirection(){
		return direction;
	}
	
	/**
	 * Roterar spriten ett relativt vinkelvärde.  
	 * @param deg    vinkeln i grader moturs.
	 */	
	public void rotate(double deg){
		direction += deg;
		setDirection(direction);
	}

	/**
	 * Returnerar x-positionen av bilden.
	 */
	public int getX() {
		return SimpleWindow.getConfined(label::getX) + (side - width) / 2;
	}

	/** Returnerar y-positionen av bilden. */
	public int getY() {
		return SimpleWindow.getConfined(label::getY) + (side - height) / 2;
	}

	/** Returnerar x-positionen av mitten på bilden. */
	public int getMidX() {
		return getX() + side / 2;
	}

	/** Returnerar y-positionen av mitten på bilden. */
	public int getMidY() {
		return getY() + side / 2;
	}

	/** Flyttar bilden till angiven punkt (x, y). */
	public void moveTo(int x, int y) {
		int dx = (side - width) / 2;
		int dy = (side - height) / 2;
    	try {
    		SwingUtilities.invokeAndWait(() -> label.setLocation(x - dx, y - dy));
		} catch (InvocationTargetException | InterruptedException unexpected) {
			throw new Error(unexpected);
		}
	}

	/** Visar eller gömmer bilden, beroende på parameterns värde. */
	public void setVisible(boolean visible) {
    	try {
			SwingUtilities.invokeAndWait(() -> label.setVisible(visible));
		} catch (InvocationTargetException | InterruptedException unexpected) {
			throw new Error(unexpected);
		}
	}

	/** Ger true om bilden är synlig. */
	public boolean isVisible() {
		return SimpleWindow.getConfined(label::isVisible);
	}

	/** Flyttar mittpunkten av bilden till positionen (x, y). */
	public void moveMidTo(int x, int y) {
		moveTo(x - width / 2, y - height / 2);
	}
}
