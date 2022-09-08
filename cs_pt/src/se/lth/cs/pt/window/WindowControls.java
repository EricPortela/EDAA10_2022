package se.lth.cs.pt.window;

import java.awt.Image;

/** Avancerade finesser för ett SimpleWindow. */
public class WindowControls {
    
    /** Betyder musklick. Se {@link #waitForUserInput(long)}. */
    public final static int MOUSE_EVENT   = 1;

    /** Betyder att en tangent tryckts. Se {@link #waitForUserInput(long)}. */
    public final static int KEY_EVENT     = 2;

    /** Betyder att inget hände (musklick eller tangenttryck) inom angiven tid.
        Se {@link #waitForUserInput(long)}. */
    public final static int TIMEOUT_EVENT = 3;

    /** Objekt av denna klass kan endast skapas via metoden getAdvancedControls() i SimpleWindow. */
    WindowControls() {
    }

    /** Ange textstorlek. Normal storlek är ungefär 14. */
    public void setFontSize(int size) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    /** Ange typsnitt. Exempel som fungerar på alla datorer är Font.SERIF, Font.SANS_SERIF och Font.MONOSPACED. */
    public void setFontName(String name) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    /**
     * Lägg till en Sprite-figur till detta fönster.
     * Notera att figuren inte visas förrän den ges en position
     * (med moveTo eller moveMidTo).
     * */
    public void addSprite(Sprite s) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    /**
     * Visar en inmatningsruta och låter användaren skriva in en text.
     * 
     * @return texten som användaren matat in
     */
    public String input() {
        throw new UnsupportedOperationException("not implemented");
    }
    
    /**
     * Vänta på inmatning (musklick eller tangenttryckning).
     * Metoden kan, om man vill, vänta en begränsad tid.
     *
     * @param timeout  anger hur länge (i millisekunder) vi ska vänta på inmatning
     * 
     * @return ett tal som berättar vilken sorts inmatning som hände:
     *  {@link #MOUSE_EVENT} om vi fick ett musklick,
     *  {@link #KEY_EVENT} om vi fick ett musklick,
     *  eller {@link #TIMEOUT_EVENT} om tiden löpte ut.
     */
    public int waitForUserInput(long timeout) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Ritar en bild, placerad i pennans aktuella läge. Pennans läge påverkas inte.
     * 
     * @param image   bilden som ska visas i fönstret
     */
    public void drawImage(Image image) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    /**
     * Stänger av (eller sätter på) automatisk uppdatering av
     * fönsterinnehållet. Normalt (i ett nyskapat fönster) är dessa
     * automatiska uppdateringar på, så att ritoperationer direkt
     * avspeglas på skärmen.
     * 
     * I program med intensiva animeringar kan automatiska uppdateringar
     * ge upphov till flimmer. Då kan man anropa setAutoUpdate(false)
     * och sedan anropa update() då fönsterinnehållet är redo att visas.
     * På så vis undviker man att halvfärdiga ritresultat flimrar förbi
     * på skärmen.
     */
    public void setAutoUpdate(boolean on) {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Uppdaterar fönstrets innehåll på skärmen. Normalt görs detta
     * automatiskt, men om man anropat setAutoUpdate(false) måste
     * programmet uttryckligen anropa update() vid en lämplig tidpunkt.
     */
    public void update() {
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Ställ in om muspilen ska synas eller inte
     * @param visible true om muspilen ska synas, false om den ska döljas
     */
    public void setMouseVisible(boolean visible) {
        throw new UnsupportedOperationException("not implemented");
    }
}
