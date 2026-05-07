package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Gestisce il flusso del menu prima del gioco:
 *   MENU → scelta sfondo → colore P1 → colore P2 → gioco avviato
 */
public class MenuScreen {

    // Stati del menu
    private enum Stato { MENU, SELECT_BG, SELECT_P1, SELECT_P2 } // definisco delle variabili costanti in un set di tipo enum
    private Stato stato = Stato.MENU;

    private int LARGHEZZA = Gdx.graphics.getWidth();
    private int ALTEZZA   = Gdx.graphics.getHeight();

    private SpriteBatch   batch;
    private ShapeRenderer shapes;
    private BitmapFont    fontGrande;
    private BitmapFont    fontPiccolo;

    // Anteprime degli sfondi (caricate con try/catch)
    private final Texture[] bgTextures = new Texture[2];
    private final String[]  bgNomi     = {"Sfondo 1", "Sfondo 2"};
    // Colori di fallback mostrati se il file immagine non esiste
    private final Color[]   bgFallback = {
        new Color(0.1f, 0.4f, 0.1f, 1f),   // verde campo
        new Color(0.05f, 0.05f, 0.2f, 1f),  // blu notte
    };

    // I 4 colori selezionabili per i giocatori
    private final Color[] colori      = { Color.WHITE, Color.BLACK, Color.RED, Color.BLUE };
    private final String[] nomiColori = { "Bianco",    "Nero",      "Rosso",   "Blu" };

    // Valori scelti dall'utente
    private int   bgIndex  = 0;
    private Color colorP1  = Color.WHITE;
    private Color colorP2  = Color.RED;
    private boolean pronto = false;

    // -------------------------------------------------------------------------

    public MenuScreen() {
        batch       = new SpriteBatch();
        shapes      = new ShapeRenderer();
        fontGrande  = new BitmapFont();
        fontGrande.getData().setScale(5f);
        fontPiccolo = new BitmapFont();
        fontPiccolo.getData().setScale(2.5f);

        // Carica le anteprime degli sfondi; se il file manca usa il colore di fallback
        for (int i = 0; i < 2; i++) {
            try {
                bgTextures[i] = new Texture(Gdx.files.internal("Img/bg" + (i + 1) + ".jpg"));
            } catch (Exception e) {
                bgTextures[i] = null; // disegneremo un rettangolo colorato
            }
        }
    }

    // -------------------------------------------------------------------------

    public void render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        switch (stato) {
            case MENU:      disegnaMenu();     gestisciMenu();     break;
            case SELECT_BG: disegnaSfondo();   gestisciSfondo();   break;
            case SELECT_P1: disegnaColore(1);  gestisciColore(1);  break;
            case SELECT_P2: disegnaColore(2);  gestisciColore(2);  break;
        }
    }

    // -------------------------------------------------------------------------
    // DISEGNO
    // -------------------------------------------------------------------------

    private void disegnaMenu() {
        batch.begin();
        fontGrande.setColor(Color.WHITE);
        fontGrande.draw(batch, "PONG", 320, 370);

        // Bottone PLAY
        fontGrande.setColor(Color.YELLOW);
        fontGrande.draw(batch, "PLAY", 330, 230);
        batch.end();
    }

    private void disegnaSfondo() {
        // Mostra 3 anteprime (200x130) affiancate
        batch.begin();
        fontPiccolo.setColor(Color.WHITE);
        fontPiccolo.draw(batch, "Scegli lo sfondo:", 250, 460);
        batch.end();

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < 2; i++) {
            float x = 80 + i * 220;
            float y = 230;
            // Bordo giallo se selezionato
            if (i == bgIndex) {
                shapes.setColor(Color.YELLOW);
                shapes.rect(x - 4, y - 4, 208, 138);
            }
            // Colore di fallback se la texture non è caricata
            if (bgTextures[i] == null) {
                shapes.setColor(bgFallback[i]);
                shapes.rect(x, y, 200, 130);
            }
        }
        shapes.end();

        // Disegna le texture degli sfondi se disponibili
        batch.begin();
        for (int i = 0; i < 2; i++) {
            float x = 80 + i * 220;
            if (bgTextures[i] != null) {
                batch.draw(bgTextures[i], x, 230, 200, 130);
            }
            fontPiccolo.setColor(Color.WHITE);
            fontPiccolo.draw(batch, bgNomi[i], x + 50, 220);
        }
        fontPiccolo.setColor(Color.LIGHT_GRAY);
        fontPiccolo.draw(batch, "Clicca per scegliere, poi premi INVIO", 170, 160);
        batch.end();
    }

    private void disegnaColore(int player) {
        batch.begin();
        fontPiccolo.setColor(Color.WHITE);
        fontPiccolo.draw(batch, "Colore Giocatore " + player + ":", 240, 420);
        batch.end();

        Color sceltaCorrente = (player == 1) ? colorP1 : colorP2;

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < colori.length; i++) {
            float x = 120 + i * 150;
            float y = 220;
            // Bordo giallo attorno al colore selezionato
            if (colori[i].equals(sceltaCorrente)) {
                shapes.setColor(Color.YELLOW);
                shapes.rect(x - 5, y - 5, 110, 110);
            }
            shapes.setColor(colori[i]);
            shapes.rect(x, y, 100, 100);
        }
        shapes.end();

        batch.begin();
        for (int i = 0; i < nomiColori.length; i++) {
            fontPiccolo.setColor(Color.WHITE);
            fontPiccolo.draw(batch, nomiColori[i], 130 + i * 150, 210);
        }
        fontPiccolo.setColor(Color.LIGHT_GRAY);
        fontPiccolo.draw(batch, "Clicca per scegliere, poi premi INVIO", 170, 150);
        batch.end();
    }

    // -------------------------------------------------------------------------
    // INPUT – il click del mouse viene rilevato con isButtonJustPressed()
    // Nota: Gdx.input.getY() ha Y=0 in ALTO, va invertito per LibGDX (Y=0 in basso)
    // -------------------------------------------------------------------------

    private void gestisciMenu() {
        // Controlla click sul bottone PLAY (area approssimativa del testo)
        if (clicked(290, 195, 220, 55)) {
            stato = Stato.SELECT_BG;
        }
    }

    private void gestisciSfondo() {
        // Click sulle 3 anteprime
        for (int i = 0; i < 3; i++) {
            if (clicked(80 + i * 220, 230, 200, 130)) {
                bgIndex = i;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            stato = Stato.SELECT_P1;
        }
    }

    private void gestisciColore(int player) {
        // Click sui 4 quadrati colore
        for (int i = 0; i < colori.length; i++) {
            if (clicked(120 + i * 150, 220, 100, 100)) {
                if (player == 1) colorP1 = colori[i];
                else             colorP2 = colori[i];
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (player == 1) stato = Stato.SELECT_P2;
            else             pronto = true; // tutte le selezioni fatte
        }
    }

    /** Restituisce true quando tutte le selezioni sono completate. */
    private boolean clicked(float x, float y, float w, float h) {
        if (!Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) return false;
        float mx = Gdx.input.getX();
        float my = ALTEZZA - Gdx.input.getY(); // inverti Y: LibGDX ha Y=0 in basso
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    // -------------------------------------------------------------------------

    public boolean isReady()    { return pronto; }
    public int     getBgIndex() { return bgIndex; }
    public Color   getColorP1() { return colorP1; }
    public Color   getColorP2() { return colorP2; }

    public void dispose() {
        batch.dispose();
        shapes.dispose();
        fontGrande.dispose();
        fontPiccolo.dispose();
        for (Texture t : bgTextures) { if (t != null) t.dispose(); }
    }
}
