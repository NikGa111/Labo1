package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

/**
 * Classe base per Ball e Paddle.
 * Contiene il Rectangle condiviso e obbliga le sottoclassi a implementare update().
 */
public abstract class GameObject {

    protected Rectangle bounds;

    public GameObject(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    /** Ogni sottoclasse aggiorna la propria logica (polimorfismo). */
    public abstract void update(float dt);

    public Rectangle getBounds()    { return bounds; }
    public float getX()             { return bounds.x; }
    public float getY()             { return bounds.y; }
    public float getWidth()         { return bounds.width; }
    public float getHeight()        { return bounds.height; }
    public void setPosition(float x, float y) { bounds.setPosition(x, y); }
}
