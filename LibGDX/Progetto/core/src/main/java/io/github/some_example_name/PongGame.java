package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Pong 2 Giocatori – tradotto da pong_pvp.py
 *
 * Controlli:
 *   Giocatore 1: W / S
 *   Giocatore 2: freccia SU / GIÙ
 *   M = pausa
 */
public class PongGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private BitmapFont fontGrande;
    private BitmapFont fontPiccolo;

    // ShapeRenderer: disegna rettangoli e forme senza usare Texture (non presente in MainGame)
    private ShapeRenderer shapes;

    // Sprite: immagine della palla generata a codice con Pixmap (non presente in MainGame)
    private Sprite  spritePalla;
    private Texture texturePalla;

    // Colori di default se l'immagine non è trovata (devono corrispondere a MenuScreen.bgFallback)
    private static final Color[] SFONDI = {
        new Color(0.05f, 0.05f, 0.2f, 1f),
        new Color(0.1f,  0.4f,  0.1f, 1f)
    };

    // Sfondo scelto nel menu: texture se trovata, altrimenti colore di default
    private Texture texturaSfondo;

    // Sound: audio per rimbalzo e goal (non presente in MainGame)
    private Sound suonoPalla;
    private Sound suonoGoal;

    private static final int LARGHEZZA = 800;
    private static final int ALTEZZA   = 500;

    private Ball   palla;
    private Paddle player1;
    private Paddle player2;

    // ScoreManager: gestisce punteggi e salvataggio su file (non presente in MainGame)
    private ScoreManager scoreManager;

    private boolean inPausa   = false;
    private int     selezione = 0;
    private final String[] opzioni = {"Riprendi", "Riavvia", "Esci"};

    // Countdown: rimpiazza time.sleep() di Python usando getDeltaTime() (non bloccante)
    private boolean inCountdown    = false;
    private float   tempoContdown  = 0f;
    private int     numeroMostrato = 3;

    // Opzioni scelte nel menu
    private final int   bgIndex;
    private final Color colorP1;
    private final Color colorP2;

    // -------------------------------------------------------------------------

    /** Costruttore con le selezioni fatte nel menu. */
    public PongGame(int bgIndex, Color colorP1, Color colorP2) {
        this.bgIndex  = bgIndex;
        this.colorP1  = colorP1;
        this.colorP2  = colorP2;
    }

    @Override
    public void create() {
        batch       = new SpriteBatch();
        fontGrande  = new BitmapFont();
        fontGrande.getData().setScale(4f);
        fontPiccolo = new BitmapFont();
        fontPiccolo.getData().setScale(2.5f);

        shapes = new ShapeRenderer();

        // Carica lo sfondo scelto; se il file non esiste usa il colore di default
        try {
            texturaSfondo = new Texture(Gdx.files.internal("bg" + (bgIndex + 1) + ".png"));
        } catch (Exception e) {
            texturaSfondo = null;
        }

        // Crea la texture della palla disegnando un cerchio su un Pixmap (immagine in memoria)
        Pixmap pm = new Pixmap(20, 20, Pixmap.Format.RGBA8888);
        pm.setColor(255 / 255f, 123 / 255f, 0f, 1f);
        pm.fillCircle(10, 10, 10);
        texturePalla = new Texture(pm);
        pm.dispose();
        spritePalla = new Sprite(texturePalla);

        // Carica i suoni; se i file non esistono il gioco parte lo stesso (try/catch)
        try { suonoPalla = Gdx.audio.newSound(Gdx.files.internal("Sound/boing.mp3")); }
        catch (Exception e) { suonoPalla = null; }
        try { suonoGoal  = Gdx.audio.newSound(Gdx.files.internal("Sound/siuuu.mp3")); }
        catch (Exception e) { suonoGoal = null; }

        scoreManager = new ScoreManager();
        scoreManager.carica();

        RiavviaGioco();
        AvviaCountdown();
    }

    // -------------------------------------------------------------------------

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        GestisciInput(dt);
        Aggiorna(dt);
        Disegna();
    }

    // -------------------------------------------------------------------------

    private void GestisciInput(float dt) {
        if (inCountdown) return;

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            inPausa = !inPausa;
            selezione = 0;
            if (!inPausa) AvviaCountdown();
        }

        if (inPausa) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                selezione = (selezione - 1 + opzioni.length) % opzioni.length;
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                selezione = (selezione + 1) % opzioni.length;
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
                ConfermaSelezione();
            return;
        }

        player1.update(dt);
        player2.update(dt);
    }

    private void Aggiorna(float dt) {
        if (inCountdown) {
            tempoContdown -= dt;
            if (tempoContdown <= 0f) {
                numeroMostrato--;
                tempoContdown = 1f;
                if (numeroMostrato <= 0) inCountdown = false;
            }
            return;
        }

        if (inPausa) return;

        palla.update(dt);

        if (palla.getBounds().overlaps(player1.getBounds()) ||
            palla.getBounds().overlaps(player2.getBounds())) {
            palla.rimbalzaOrizzontale();
            if (suonoPalla != null) suonoPalla.play();
        }

        if (palla.uscitaSinistra()) {
            scoreManager.goalP2();
            if (suonoGoal != null) suonoGoal.play();
            scoreManager.salva();
            Reset();
        }

        if (palla.uscitaDestra()) {
            scoreManager.goalP1();
            if (suonoGoal != null) suonoGoal.play();
            scoreManager.salva();
            Reset();
        }
    }

    private void Disegna() {
        // Sfondo: texture se trovata, altrimenti colore di default
        if (texturaSfondo != null) {
            ScreenUtils.clear(0f, 0f, 0f, 1f);
            batch.begin();
            batch.draw(texturaSfondo, 0, 0, LARGHEZZA, ALTEZZA);
            batch.end();
        } else {
            Color bg = SFONDI[bgIndex];
            ScreenUtils.clear(bg.r, bg.g, bg.b, 1f);
        }

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.DARK_GRAY);
        shapes.rect(LARGHEZZA / 2f - 2, 0, 4, ALTEZZA);
        shapes.setColor(colorP1);  // colore scelto nel menu
        shapes.rect(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
        shapes.setColor(colorP2);  // colore scelto nel menu
        shapes.rect(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
        if (inPausa) {
            shapes.setColor(new Color(0.2f, 0.2f, 0.2f, 0.9f));
            shapes.rect(250, 150, 300, 220);
        }
        shapes.end();

        batch.begin();

        spritePalla.setPosition(palla.getX(), palla.getY());
        spritePalla.draw(batch);

        fontGrande.setColor(colorP1);
        fontGrande.draw(batch, String.valueOf(scoreManager.getPunteggioP1()), 300, ALTEZZA - 10);
        fontGrande.setColor(colorP2);
        fontGrande.draw(batch, String.valueOf(scoreManager.getPunteggioP2()), 470, ALTEZZA - 10);

        if (inCountdown) {
            fontGrande.setColor(Color.WHITE);
            fontGrande.draw(batch, String.valueOf(numeroMostrato), LARGHEZZA / 2f - 15, ALTEZZA / 2f + 30);
        }

        if (inPausa) {
            fontGrande.setColor(Color.WHITE);
            fontGrande.draw(batch, "PAUSA", 315, 350);
            for (int i = 0; i < opzioni.length; i++) {
                fontPiccolo.setColor(i == selezione ? Color.YELLOW : Color.WHITE);
                fontPiccolo.draw(batch, "> " + opzioni[i], 285, 290 - i * 45);
            }
        }

        batch.end();
    }

    // -------------------------------------------------------------------------

    private void AvviaCountdown() {
        inCountdown    = true;
        numeroMostrato = 3;
        tempoContdown  = 1f;
    }

    private void Reset() {
        palla.reset();
        player1.reset();
        player2.reset();
        AvviaCountdown();
    }

    private void RiavviaGioco() {
        if (scoreManager != null) scoreManager.reset();
        palla   = new Ball(390, 240, 250f, 250f);
        player1 = new Paddle(30,  200, Input.Keys.W,  Input.Keys.S);
        player2 = new Paddle(750, 200, Input.Keys.UP, Input.Keys.DOWN);
    }

    private void ConfermaSelezione() {
        switch (selezione) {
            case 0: inPausa = false; AvviaCountdown(); break;
            case 1: RiavviaGioco(); inPausa = false; AvviaCountdown(); break;
            case 2: scoreManager.salva(); Gdx.app.exit(); break;
        }
    }

    // -------------------------------------------------------------------------

    @Override
    public void dispose() {
        batch.dispose();
        shapes.dispose();
        fontGrande.dispose();
        fontPiccolo.dispose();
        texturePalla.dispose();
        if (texturaSfondo != null) texturaSfondo.dispose();
        if (suonoPalla != null) suonoPalla.dispose();
        if (suonoGoal  != null) suonoGoal.dispose();
    }
}
