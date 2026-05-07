package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Gestisce i punteggi e li salva su file.
 * Usa Gdx.files.absolute() per scrivere in un percorso specifico su disco.
 */
public class ScoreManager {

    // Percorso assoluto dove viene salvato il file dei punteggi
    private static final String PERCORSO =
        "C:/DatiAllievo/Gits/Labo1/LibGDX/Progetto/core/src/main/java/io/github/some_example_name/punteggi.txt";

    private int punteggioP1 = 0;
    private int punteggioP2 = 0;

    public void goalP1() { punteggioP1++; }
    public void goalP2() { punteggioP2++; }
    public void reset()  { punteggioP1 = 0; punteggioP2 = 0; }

    public int getPunteggioP1() { return punteggioP1; }
    public int getPunteggioP2() { return punteggioP2; }

    /** Scrive i punteggi nel file. Il try/catch evita crash se il percorso non è accessibile. */
    public void salva() {
        try {
            FileHandle file = Gdx.files.absolute(PERCORSO);
            file.writeString("P1:" + punteggioP1 + "\nP2:" + punteggioP2, false);
        } catch (Exception e) {
            Gdx.app.error("ScoreManager", "Salvataggio fallito: " + e.getMessage());
        }
    }

    /** Legge i punteggi dal file. Se il file non esiste parte da zero senza crash. */
    public void carica() {
        try {
            FileHandle file = Gdx.files.absolute(PERCORSO);
            if (file.exists()) {
                String[] righe = file.readString().split("\n");
                punteggioP1 = Integer.parseInt(righe[0].replace("P1:", "").trim());
                punteggioP2 = Integer.parseInt(righe[1].replace("P2:", "").trim());
            }
        } catch (Exception e) {
            Gdx.app.error("ScoreManager", "Caricamento fallito: " + e.getMessage());
        }
    }
}
