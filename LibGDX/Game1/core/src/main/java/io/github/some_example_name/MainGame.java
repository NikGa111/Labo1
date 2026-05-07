package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture flappy;
    private Texture background;
    private Texture pipe;

    private BitmapFont font;
    private int score = 0;

    private Rectangle flappyBounds;
    private Rectangle groundBounds;
    private boolean isGameOver = false;

    private int flappyY = 40;
    private int flappyX = 80;
    private float velY = 400;
    private float GRAVITY = -500;
    private float FLAP_FORCE = 200;

    @Override
    public void create() {
        batch = new SpriteBatch();
        flappy = new Texture("flappy.png");
        background = new Texture("background.png");
        pipe = new Texture("pipe.png");
        font = new BitmapFont();
        font.setColor(Color.FIREBRICK);
        font.getData().setScale(3.0f);
        flappyBounds = new Rectangle(flappyX, flappyY, flappy.getWidth(), flappy.getHeight());
        groundBounds = new Rectangle(0,0, Gdx.graphics.getWidth(),50);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        if(!isGameOver){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()){
                velY = FLAP_FORCE;
            }
            velY += GRAVITY * dt;
            flappyY += velY * dt;
            flappyBounds.y = flappyY;

            //controlla collisione con suolo
            if(flappyBounds.overlaps(groundBounds)){
                isGameOver = true;
            }
        }else{
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)|| Gdx.input.justTouched()) {
                RestartGame();
            }
        }
        Draw();
    }

    private void Draw(){
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(pipe, 200, 50, pipe.getWidth(),200);
        batch.draw(flappy, flappyX, flappyY, 60, flappy.getHeight()-20);
        font.draw(batch, "Score: " + score, 2, Gdx.graphics.getHeight() - 20);
        if (isGameOver) {
            font.draw(batch, "Game Over: ",
                Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        }

        batch.end();
    }

    private void RestartGame(){
        flappyY = 200;
        flappyX = 120;
        score = 0;
        flappyBounds.y = flappyY;
        velY = 0;
        isGameOver = false;
    }

    @Override
    public void dispose() {
        batch.dispose();
        flappy.dispose();
        background.dispose();
        pipe.dispose();
        font.dispose();
        batch.dispose();
    }
}
