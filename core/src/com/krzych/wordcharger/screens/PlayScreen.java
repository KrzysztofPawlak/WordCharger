package com.krzych.wordcharger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;
import com.krzych.wordcharger.scenes.Hud;

/**
 * Created by Krzysiek on 2017-03-14.
 */

public class PlayScreen implements Screen {

    private WordCharger game;
    Texture img;
    private OrthographicCamera camera;
    private Viewport view;
    private Hud hud;

    public PlayScreen(WordCharger game) {
        this.game = game;
        img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera();
        view = new StretchViewport(800, 480, camera); // TODO more universal

        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
