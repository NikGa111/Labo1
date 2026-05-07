package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

/** Racchetta del gioco. Estende GameObject (ereditarietà). */
public class Paddle extends GameObject {

    private static final float VELOCITA = 300f;
    private static final int ALTEZZA_SCHERMO = 500;

    private final int tastoSu;
    private final int tastoGiu;
    private final float spawnX;
    private final float spawnY;

    public Paddle(float x, float y, int tastoSu, int tastoGiu) {
        super(x, y, 20, 100);
        this.tastoSu  = tastoSu;
        this.tastoGiu = tastoGiu;
        this.spawnX   = x;
        this.spawnY   = y;
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(tastoSu)  && bounds.y + bounds.height < ALTEZZA_SCHERMO)
            bounds.y += VELOCITA * dt;
        if (Gdx.input.isKeyPressed(tastoGiu) && bounds.y > 0)
            bounds.y -= VELOCITA * dt;
    }

    public void reset() { setPosition(spawnX, spawnY); }
}
