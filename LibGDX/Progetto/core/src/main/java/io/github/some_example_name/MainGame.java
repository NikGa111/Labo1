package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

/**
 * Entry point del gioco. Gestisce il passaggio dal menu al gioco.
 * Nel DesktopLauncher sostituire "new PongGame()" con "new Main()".
 * I commenti scritti in questo formato sono generati con AI.
 * In alcune parti ha modificato anche l'indentazione ma solo dove eplicitato il codice è generato dall'AI.
 */
public class MainGame extends ApplicationAdapter {

    private MenuScreen menu;
    private PongGame   game;

    @Override
    public void create() {
        menu = new MenuScreen();
    }

    @Override
    public void render() {
        // Finché il menu non ha finito le selezioni, disegna il menu
        if (game == null) {
            menu.render();
            if (menu.isReady()) {
                // Menu completato: avvia il gioco con le opzioni scelte
                game = new PongGame(menu.getBgIndex(), menu.getColorP1(), menu.getColorP2());
                game.create();
                menu.dispose();
            }
        } else {
            game.render();
        }
    }

    @Override
    public void dispose() {
        if (game != null) game.dispose();
        else              menu.dispose();
    }
}
