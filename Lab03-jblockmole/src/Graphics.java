import se.lth.cs.pt.window.SimpleWindow;

public class Graphics {

    private int width;
    private int blockSize;
    private int height;

    private SimpleWindow w;

    public Graphics(int w, int h, int bs) {
        this.width = w;
        this.blockSize = bs;
        this.height = h;

        this.w = new SimpleWindow(width * blockSize, height * blockSize, "Digging");
    }
    
    public void square(){
        w.moveTo(10, 10);
		w.lineTo(10, 20);
		w.lineTo(20, 20);
		w.lineTo(20, 10);
		w.lineTo(10, 10);
    }

    public void block(int x, int y, java.awt.Color c) {
        int left = x * blockSize; //Ex. 0*10 = 0
        int right = left + blockSize - 1; //0 + 10 - 1 = 9
        int top = y * blockSize; //0 * 10 = 0
        int bottom = top + blockSize - 1; //= 9
        w.setLineColor(c);
        for(int row = top; row <= bottom; row ++){ //row = 0; row <= 9; row++
            w.moveTo(left, row);
            w.lineTo(right, row);
        }
    }

    public void rectangle(int x, int y, int width, int height, java.awt.Color c) {
        for (int yy = y; yy < y + height; yy++){
            for(int xx = x; xx < x + width; xx++){
                block(xx, yy, c);
            }
        }
    }
    
    public char waitForKey() {
        return w.waitForKey();
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
