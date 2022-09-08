package se.lth.cs.pt.window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;
import javax.swing.*;

/**
 * Ett ritfönster, ungefär som det som använts i
 * "Objektorienterad programmering och Java".
 * 
 * @author Per Holm
 * @author Maj Stenmark
 * @author Björn Regnell
 * @author Patrik Persson
 */
public class SimpleWindow {

    public static final int MOUSE_EVENT   = WindowControls.MOUSE_EVENT;
    public static final int KEY_EVENT     = WindowControls.KEY_EVENT;
    public static final int TIMEOUT_EVENT = WindowControls.TIMEOUT_EVENT;

    private final SimpleWindowFrame frame;

    /**
     * Skapar ett fönster och gör det synligt.
     * 
     * @param width   fönstrets bredd (räknat i pixlar)
     * @param height  fönstrets höjd (räknat i pixlar)
     * @param title   fönstrets titel
     */
    public SimpleWindow(int width, int height, String title) {
        WindowStateMonitor stateMonitor = new WindowStateMonitor();
        frame = getConfined(() -> new SimpleWindowFrame(width, height, title, stateMonitor));
        stateMonitor.awaitWindowActive();
    }

    /**
     * Tar reda på fönstrets storlek i x-led.
     * 
     * @return fönstrets bredd
     */
    public int getWidth() {
        return getConfined(frame.pixels::getWidth);
    }

    /**
     * Tar reda på fönstrets storlek i y-led.
     * 
     * @return fönstrets höjd
     */
    public int getHeight() {
        return getConfined(frame.pixels::getHeight);
    }

    /** Raderar innehållet i fönstret. */
    public void clear() {
        SwingUtilities.invokeLater(frame::clear);
    }

    /** Stänger fönstret tillfälligt. */
    public void close() {
        SwingUtilities.invokeLater(frame::close);
    }

    /** Öppnar ett stängt fönster. */
    public void open() {
        SwingUtilities.invokeLater(frame::open);
    }

    /**
     * Flyttar pennan till punkten x,y utan att rita.
     * 
     * @param x   x-koordinat för pennans nya position
     * @param y   y-koordinat för pennans nya position
     */
    public void moveTo(int x, int y) {
        SwingUtilities.invokeLater(() -> frame.moveTo(x, y));
    }

    /**
     * Flyttar pennan till punkten x,y och ritar samtidigt en rät linje.
     * 
     * @param x   x-koordinat för pennans nya position
     * @param y   y-koordinat för pennans nya position
     */
    public void lineTo(int x, int y) {
        SwingUtilities.invokeLater(() -> frame.lineTo(x, y));
    }

    /**
     * Skriver texten txt med början i pennans aktuella läge. Pennans läge påverkas inte.
     * 
     * @param txt   texten som ska skrivas i fönstret
     */
    public void writeText(String txt) {
        SwingUtilities.invokeLater(() -> frame.writeText(txt));
    }
    
    /**
     * Tar reda på x-koordinaten för pennans läge.
     * 
     * @return pennans x-koordinat
     */
    public int getX() {
        return getConfined(() -> frame.penX);
    }

    /**
     * Tar reda på y-koordinaten för pennans läge.
     * 
     * @return pennans y-koordinat
     */
    public int getY() {
        return getConfined(() -> frame.penY);
    }

    /**
     * Sätter linjebredden till thickness pixlar
     * 
     * @param thickness   linjebredd (i pixlar, där 1 är normal bredd)
     */
    public void setLineWidth(int thickness) {
        SwingUtilities.invokeLater(() -> frame.setLineWidth(thickness));
    }

    /**
     * Sätter linjefärgen till col. Det finns ett antal inbyggda färger, som
     * Color.RED eller Color.MAGENTA. Man kan även skapa nya, egna färger:
     * 
     * <pre>
     * {@code
     *   SimpleWindow w = new SimpleWindow(300, 300, "colordemo");
     *   w.setLineColor(Color.RED);
     *   w.moveTo(100, 100);
     *   w.lineTo(200, 100);                        // en röd linje
     *   
     *   Color mycolor = new Color(242, 128, 161);  // en helt egen färg
     *   w.setLineColor(mycolor);
     *   w.lineTo(200, 200);                        // en linje i denna färg
     *   ...
     * }
     * </pre>
     * 
     * @param col   den nya linjefärgen
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html">dokumentation för java.awt.Color</a>
     */
    public void setLineColor(Color col) {
        SwingUtilities.invokeLater(() -> frame.pen.setColor(col));
    }

    /**
     * Tar reda på linjebredden.
     * 
     * @return linjebredden (i pixlar)
     */
    public int getLineWidth() {
        return getConfined(frame::getLineWidth);
    }

    /**
     * Tar reda på linjefärgen.
     * 
     * @return linjefärg
     */
    public Color getLineColor() {
        return getConfined(() -> frame.pen.getColor());
    }

    /**
     * Tar reda på x-koordinaten för musens position just nu.
     * 
     * @return muspekarens x-koordinat, relativt fönstret
     */
    public int getMouseX() {
        return getConfined(() -> {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, frame.panel);
            return (int) p.getX();
        });
    }

    /**
     * Tar reda på y-koordinaten för musens position just nu.
     * 
     * @return muspekarens y-koordinat, relativt fönstret
     */
    public int getMouseY() {
        return getConfined(() -> {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, frame.panel);
            return (int) p.getY();
        });
    }

    /** Väntar tills användaren har klickat på en musknapp. */
    public void waitForMouseClick() {
        event.await(MouseEvent.class, INFINITE_TIMEOUT);
    }

    /**
     * Tar reda på x-koordinaten för muspekarens position vid senaste musklick.
     * 
     * @return klickets x-koordinat
     */
    public int getClickedX() {
        return event.getClickedX();
    }

    /**
     * Tar reda på y-koordinaten för muspekarens position vid senaste musklick.
     * 
     * @return klickets y-koordinat
     */
    public int getClickedY() {
        return event.getClickedY();
    }

    /**
     * Väntar tills användaren trycker ned en tangent på tangentbordet.
     * 
     * @return den tryckta tangenten
     */
    public char waitForKey() {
        return event.await(KeyEvent.class, INFINITE_TIMEOUT).getKeyChar();
    }

    /**
     * Väntar tills användaren antingen klickar på en musknapp eller trycker ned en
     * tangent på tangentbordet.
     */
    public void waitForEvent() {
        event.await(InputEvent.class, INFINITE_TIMEOUT);
    }

    /**
     * Tar reda på vilket slags händelse som inträffat (MOUSE_EVENT eller KEY_EVENT).
     * 
     * @return KEY_EVENT för tangenttryck, MOUSE_EVENT för musklick
     */
    public int getEventType() {
        return event.getType();
    }

    /**
     * Tar reda på vilken tangent som trycktes ned vid en KEY_EVENT-händelse.
     * 
     * @return teckenkoden för tangenten
     */
    public char getKey() {
        return event.getKey();
    }

    /**
     * Väntar ms millisekunder.
     * 
     * @param ms   antalet millisekunder programmet ska pausa
     */
    public static void delay(int ms) {
        if (ms > 0) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException unexpected) {
                throw new Error(unexpected);
            }
        }
    }

    /** Hämtar avancerade kontroller för detta fönster. */
    public WindowControls getAdvancedControls() {
        return frame.controls;
    }

    // returnerar ett värde av typ T från en getter, anropad i Swings EDT
    // (thread confinement)
    /* package */ static <T> T getConfined(Callable<T> getter) {
        try {
            CompletableFuture<T> result = new CompletableFuture<T>();
            SwingUtilities.invokeLater(() -> {
                try {
                    result.complete(getter.call());
                } catch (Exception e) {
                    throw new Error(e);
                }
            });
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new Error(e);
        }
    }

    // =======================================================================
    
    // maximal tid vi väntar på att fönstret ska bli aktivt
    private static final int WINDOW_OPENING_TIMEOUT = 3000;
    
    // Vi pollar för att upptäcka när fönstret faktiskt är aktivt, för
    // att på så vis fördröja applikationen tills fönstret faktiskt funkar.
    // Detta är ingen perfekt lösning, men så långe inget WindowEvent
    // eller WindowStateEvent rapporteras finns inget bättre att göra. Väl?
    private static final int WINDOW_OPENING_POLLING_PERIOD = 50;

    private class WindowStateMonitor {

    	private boolean isActive = false;
		
		private synchronized void awaitWindowActive() {
			long now = System.currentTimeMillis();
			long deadline = now + WINDOW_OPENING_TIMEOUT;
			try {
				while (!isActive && now < deadline) {
					wait(deadline - now);
					now = System.currentTimeMillis();
				}
//				if (now >= deadline) {
//					System.out.println("Varning: fönstret inte aktivt inom " + WINDOW_OPENING_TIMEOUT + "ms.");
//				}
			} catch (InterruptedException unexpected) {
				throw new Error(unexpected);
			}
		}

		private synchronized void notifyWindowActive() {
			isActive = true;
			notifyAll();
		}
    }

    // =======================================================================

    private static int nbrOpenFrames = 0;

    @SuppressWarnings("serial")
    private class SimpleWindowFrame extends JFrame {

        // storlek på inmatningsfält
        private static final int INPUT_FIELD_HEIGHT = 40;
        
        // attributen nedan accessas endast från EDT (thread confinement)
        private int penX        = 0;
        private int penY        = 0;
        private int fontSize    = 14;
        private String fontName = Font.SANS_SERIF;
        private BasicStroke stroke;

        private boolean autoUpdate = true;
        
        private final BufferedImage pixels;
        private final Graphics2D pen;

        private final JTextField inputField = new JTextField();
        private final JPanel panel          = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(pixels, 0, 0, this);
            }
        };

        /** Om title är exakt "FULLSCREEN" sätts fönstret i fullskärm */
        private SimpleWindowFrame(int width, int height, String title, WindowStateMonitor monitor) {
            super(title);

            // När det sista fönstret stängs, avsluta programmet
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    nbrOpenFrames--;
                    if (nbrOpenFrames > 0) {
                        setVisible(false);
                        dispose();
                    } else {
                        System.exit(0);
                    }
                }
            });

            if (title.equals("FULLSCREEN")) {
                setUndecorated(true);
                GraphicsDevice gd = getGraphicsConfiguration().getDevice();

                DisplayMode[] modes = gd.getDisplayModes();
                DisplayMode bestMode = gd.getDisplayMode();
                int distance = Integer.MAX_VALUE;

                for (DisplayMode m : modes) {
                    int wAdditional = m.getWidth() - width;
                    int hAdditional = m.getHeight() - height;
                    if (wAdditional >= 0 && hAdditional >= 0) {
                        int d = wAdditional + hAdditional;
                        if (d < distance) {
                            bestMode = m;
                            distance = d;
                        }
                    }
                }

                gd.setFullScreenWindow(SimpleWindowFrame.this);
                gd.setDisplayMode(bestMode);
                width = bestMode.getWidth();
                height = bestMode.getHeight();
            }
            
            new Timer(WINDOW_OPENING_POLLING_PERIOD, e -> {
            	if (SimpleWindowFrame.this.isActive()) {
            		monitor.notifyWindowActive();
            		Object source = e.getSource();
            		if (source instanceof Timer) {
            			((Timer) source).stop();
            		}
            	}
            }).start();

            pixels = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            pen = pixels.createGraphics();
            pen.setColor(Color.BLACK);
            pen.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            pen.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            pen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            setLineWidth(1);

            panel.setLayout(null);
            panel.setPreferredSize(new Dimension(width, height));
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    event.submit(e);
                }
            });
            panel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    event.submit(e);
                }
            });

            add(panel);

            inputField.setBounds(0,  height - INPUT_FIELD_HEIGHT,  width,  INPUT_FIELD_HEIGHT);
            inputField.setVisible(false);
            panel.add(inputField);
            pack();

            setResizable(false);
            setLocationRelativeTo(null);

            clear();
            open();
        }

        private void clear() {
            Graphics eraser = pen.create();
            eraser.setColor(Color.WHITE);
            eraser.fillRect(0, 0, getWidth(), getHeight());
            eraser.dispose();
            updateWindow();
        }

        private void close() {
            if (isVisible()) {
                setVisible(false);
                nbrOpenFrames--;
            }
        }

        private void open() {
            if (!isVisible()) {
                setVisible(true);
                nbrOpenFrames++;
            }
        }

        private void moveTo(int x, int y) {
            penX = x;
            penY = y;
        }

        private void lineTo(int x, int y) {
            pen.drawLine(penX, penY, x, y);
            int width = 2 + 2 * (int) Math.ceil(stroke.getLineWidth()); // för att vara på den säkra sidan

            int ux = penX;
            int uy = penY;
            int dx = x - penX;
            int dy = y - penY;
            if (dx < 0) {
                ux = x;
                dx = -dx;
            }
            if (dy < 0) {
                uy = y;
                dy = -dy;
            }
            updateWindow(ux - width, uy - width, dx + 2 * width, dy + 2 * width);

            moveTo(x, y);
        }

        private void setLineWidth(int lineWidth) {
            stroke = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
            pen.setStroke(stroke);
        }

        private int getLineWidth() {
            return Math.round(stroke.getLineWidth());
        }
        
        private void writeText(String txt) {
            pen.setFont(new Font(fontName, Font.PLAIN, fontSize));
            pen.drawString(txt, penX, penY);
            updateWindow();
        }

        private void updateWindow() {
            if (autoUpdate) {
                panel.repaint();
            }
        }

        private void updateWindow(int x, int y, int w, int h) {
            if (autoUpdate) {
                panel.repaint(x, y, w, h);
            }
        }
        
        // -------------------------------------------------------------------

        private class SimpleWindowControls extends WindowControls {

            @Override
            public void setFontSize(int size) {
                SwingUtilities.invokeLater(() -> fontSize = size);
            }

            @Override
            public void setFontName(String name) {
                SwingUtilities.invokeLater(() -> fontName = name);
            }

            @Override
            public void addSprite(Sprite s) {
                try {
					SwingUtilities.invokeAndWait(() -> panel.add(s.label));
					// Vi skippar repaint() här, eftersom applikationen säkert
					// vill placera Spriten på en lämplig plats (och då kommer
					// repaint() att ske). Om man gör repaint() här kommer Spriten
					// då att flimra förbi i övre vänstra hörnet.
				} catch (InvocationTargetException | InterruptedException unexpected) {
					throw new Error(unexpected);
				}
            }

            @Override
            public String input() {
                try {
                    final CompletableFuture<String> userInput = new CompletableFuture<>();
                    final ActionListener listener = e -> userInput.complete(inputField.getText());
                    SwingUtilities.invokeLater(() -> {
                        inputField.setText("");
                        inputField.addActionListener(listener);
                        inputField.setVisible(true);
                        pack();
                        inputField.requestFocus();
                    });
                    String s = userInput.get();
                    SwingUtilities.invokeLater(() -> {
                        inputField.setVisible(false);
                        inputField.removeActionListener(listener);
                        pack();
                    });
                    return s;
                } catch (ExecutionException | InterruptedException e) {
                    throw new Error(e);
                }
            }

            @Override
            public int waitForUserInput(long timeout) {
                event.await(InputEvent.class, timeout);
                return event.getType();
            }

            @Override
            public void drawImage(Image image) {
                SwingUtilities.invokeLater(() -> {
                    pen.drawImage(image, penX, penY, panel);
                    panel.repaint();
                });
            }

            @Override
            public void setAutoUpdate(boolean on) {
                SwingUtilities.invokeLater(() -> autoUpdate = on);
            }

            @Override
            public void update() {
                if (getConfined(() -> autoUpdate)) {
                    throw new IllegalStateException("Called update() with automatic updates on -- call setAutoUpdate(false) first");
                }
                SwingUtilities.invokeLater(panel::repaint);
            }

            @Override
            public void setMouseVisible(boolean visible) {
                SwingUtilities.invokeLater(() -> {
                    Cursor c;
                    if (visible) {
                        c = Cursor.getDefaultCursor();
                    } else {
                        c = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null);
                    }
                    setCursor(c);
                });
            }
        }

        private final SimpleWindowControls controls = new SimpleWindowControls();
    }

    // =======================================================================

    // Särskilt timeout-värde, som betyder att vi ska vänta utan time-out alls
    private static final long INFINITE_TIMEOUT = -1L;

    // monitor för senast mottagna InputEvent
    private class EventMonitor {

        private static final int NO_EVENT = -1;

        private InputEvent ev = null;
        private int eventType = NO_EVENT;
        private int clickedX = 0;
        private int clickedY = 0;
        private char key = (char) 0;

        // Anropas från Swing-tråden
        private synchronized void submit(InputEvent e) {
            ev = e;
            notifyAll();
        }

        // Väntar på ett InputEvent, eller någon av subklasserna MouseEvent/KeyEvent.
        // Anropet blockerar upp till 'timeout' millisekunder. Returvärdet är av
        // samma klass som argumentet c (eller en subklass till c).
        @SuppressWarnings("unchecked")
        private synchronized <E extends InputEvent> E await(Class<E> c, long timeout) {
            try {
                long deadline = System.currentTimeMillis() + timeout;
                SwingUtilities.invokeLater(() -> frame.panel.requestFocus());

                do {
                    ev = null;
                    if (timeout == INFINITE_TIMEOUT) {
                        while (ev == null || !c.isInstance(ev)) {
                            wait();
                        }
                    } else {
                        long delay = deadline - System.currentTimeMillis();
                        while (delay > 0 && (ev == null || !c.isInstance(ev))) {
                            wait(delay);
                            delay = deadline - System.currentTimeMillis();
                        }
                    }
                } while (ev != null && !c.isInstance(ev));

                if (ev == null) {
                    eventType = TIMEOUT_EVENT;
                } else if (ev instanceof MouseEvent) {
                    eventType = MOUSE_EVENT;
                    clickedX = ((MouseEvent) ev).getX();
                    clickedY = ((MouseEvent) ev).getY();
                } else if (ev instanceof KeyEvent) {
                    eventType = KEY_EVENT;
                    key = ((KeyEvent) ev).getKeyChar();
                } else {
                    throw new Error("unexpected event: " + ev);
                }
                return (E) ev;
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        }

        private synchronized int getType() {
            return eventType;
        }

        private synchronized int getClickedX() {
            return clickedX;
        }

        private synchronized int getClickedY() {
            return clickedY;
        }

        private synchronized char getKey() {
            return key;
        }
    }

    private final EventMonitor event = new EventMonitor();
}
