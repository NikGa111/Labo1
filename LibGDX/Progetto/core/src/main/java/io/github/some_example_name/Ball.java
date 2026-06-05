package io.github.some_example_name;

import com.badlogic.gdx.Gdx;

/** Palla del gioco. Estende GameObject (ereditarietà).
 * I commenti scritti in questo formato sono generati con AI.
 * In alcune parti ha modificato anche l'indentazione ma solo dove eplicitato il codice è generato dall'AI.
 * */
public class Ball extends GameObject {

    private float velX;
    private float velY;

    private int LARGHEZZA_SCHERMO = Gdx.graphics.getWidth();
    private int ALTEZZA_SCHERMO   = Gdx.graphics.getHeight();

    public Ball(float x, float y, float velX, float velY) {
        super(x, y, 20, 20);
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void update(float dt) {
        bounds.x += velX * dt;
        bounds.y += velY * dt;

        if (bounds.y <= 0 || bounds.y + bounds.height >= ALTEZZA_SCHERMO)
            velY *= -1;
    }
    public void rimbalzaOrizzontale() {
        velX = -velX;

        if (velX > 0) {
            velX += 120f;
        } else {
            velX -= 120f;
        }

        velY += 1;
    }

    public void reset() {
        bounds.setPosition(390, 240);
        velX = 20000 * Gdx.graphics.getDeltaTime();
        velY = 20000 * Gdx.graphics.getDeltaTime();
    }

    public boolean uscitaSinistra() { return bounds.x + bounds.width <= 0; }
    public boolean uscitaDestra()   { return bounds.x >= LARGHEZZA_SCHERMO; }

    public float getVelX()          { return velX; }
    public float getVelY()          { return velY; }
    public void  setVelX(float v)   { velX = v; }
    public void  setVelY(float v)   { velY = v; }
}
