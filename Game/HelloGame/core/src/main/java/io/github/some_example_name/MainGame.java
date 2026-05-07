package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends ApplicationAdapter {
    ShapeRenderer sr;
    int x = 100;
    int y = 100;
    @Override public void create() {
        sr = new ShapeRenderer();
    }
    @Override public void render() {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.circle(x, y, 50);
        if ()
        x += 10;
        y += 10;
        sr.end();
    }
    @Override
    public void dispose() {
//Todo
    }
}
