package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture Fireball1;
    private Texture Fireball2;
    private Texture Kirby;

    private Random random = new Random();

    private int xFireball1 = 0;
    private int yFireball1 = 0;
    private int xFireball2 = 0;
    private int yFireball2 = 420;
    private int xKirby;
    private int yKirby;

    private int vxFireball1 = 300;
    private int vyFireball1 = 300;
    private int vxFireball2 = -400;
    private int vyFireball2 = -400;
    private int vxKirby = 200;
    private int vyKirby = 200;

    private int dirxF1 = 1;
    private int diryF1 = 1;
    private int dirxF2 = 1;
    private int diryF2 = 1;
    private int dirxK = 1;
    private int diryK = 1;

    private int count = 1;



    @Override
    public void create() {
        batch = new SpriteBatch();
        Fireball1 = new Texture("Fireball.png");
        Fireball2 = new Texture("Fireball.png");
        Kirby = new Texture("Kirby.png");
    }

    @Override
    public void render() {
        if (count == 1) {
            xKirby = random.nextInt(Gdx.graphics.getHeight() - 60);
            yKirby = random.nextInt(Gdx.graphics.getWidth() - 60);
        }

        Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        xFireball1 += vxFireball1 * dirxF1;
        yFireball1 += vyFireball1 * diryF1;
        xFireball2 += vxFireball2 * dirxF2;
        yFireball2 += vyFireball2 * diryF2;
        xKirby += vxKirby * dirxK *Gdx.graphics.getDeltaTime();
        yKirby += vyKirby * diryK * Gdx.graphics.getDeltaTime();

        if (xFireball1 < 0){
            dirxF1 = 1;
        } else if (xFireball1 > Gdx.graphics.getWidth()) {
            dirxF1 = -1;
        }
        if (yFireball1 < 0){
            diryF1 = -1;
        } else if (yFireball1 > Gdx.graphics.getWidth()) {
            diryF1 = 1;
        }
        if (xFireball2 < 0){
            dirxF2 = 1;
        } else if (xFireball2 > Gdx.graphics.getWidth()) {
            dirxF2 = -1;
        }
        if (yFireball2 < 0){
            diryF2 = -1;
        } else if (yFireball2 > Gdx.graphics.getWidth()) {
            diryF2 = 1;
        }

        batch.begin();
        batch.draw(Fireball1, xFireball1, yFireball1,60,60);
        batch.draw(Fireball2, xFireball2,  yFireball2, 60,60);
        batch.draw(Kirby, xKirby, yKirby, 60,60);
        batch.end();
    }

    @Override
    public void dispose() {

        batch.dispose();
        Fireball1.dispose();
        Fireball2.dispose();
        Kirby.dispose();
    }
}
