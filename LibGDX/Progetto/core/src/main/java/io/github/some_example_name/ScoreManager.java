package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Gestisce i punteggi e li salva su file.
 * Usa Gdx.files.absolute() per scrivere in un percorso specifico su disco.
 * I commenti scritti in questo formato sono generati con AI.
 * In alcune parti ha modificato anche l'indentazione ma solo dove eplicitato il codice è generato dall'AI.
 */
public class ScoreManager {

    // Percorso assoluto dove viene salvato il file dei punteggi
    private static final String PERCORSO =
        "./core/src/main/java/io/github/some_example_name/punteggi.txt";

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
            file.writeString("P1:" + punteggioP1 + "\nP2:" + punteggioP2, false); // l'append = false fa si che non si attacchino tutti i punteggi, se true si attaccano ed esce male e aggiunge a caso il punteggio p1=0 e p2=0
        } catch (Exception e) {
            Gdx.app.error("ScoreManager", "Salvataggio fallito: " + e.getMessage());
        }
    }

    /** Legge i punteggi dal file. Se il file non esiste parte da zero senza crash.
     * Creata con aiuto prevalente dell'AI perché prima crashava quando non c'era il carica
     * */
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
