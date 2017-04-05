package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-03-17.
 */

public class Hud {

    public Stage stage;
    private Viewport viewport;

    private Integer score;

    Label scoreLabel;
    Label levelLabel;

    public Hud(SpriteBatch sb) {
        score = 0;

        viewport = new FitViewport(WordCharger.V_WIDTH, WordCharger.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("1", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);

        stage.addActor(table);
    }
}
