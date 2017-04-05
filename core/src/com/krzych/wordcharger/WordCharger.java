package com.krzych.wordcharger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.krzych.wordcharger.screens.PlayScreen;

public class WordCharger extends Game {

    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 480;
    public static final float PPM = 100;

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
