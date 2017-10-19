package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-10-19.
 */

public class Sentence implements Disposable{

    public Stage stage;
    private Viewport viewport;

    Actor ch;

    OrthographicCamera cam;

    Batch sb;

    public Sentence(SpriteBatch sb) {
        this.sb = sb;

        cam = new OrthographicCamera();
        viewport = new FitViewport(800, 480, cam);
        stage = new Stage(viewport, WordCharger.batch);

        ch = new Character();

        stage.addActor(ch);
        System.out.println("size: " + stage.getActors().size);
    }

    public void draw() {
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
