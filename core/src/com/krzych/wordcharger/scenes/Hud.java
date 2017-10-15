package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-03-17.
 */

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private Integer score;

    Label scoreLabel;
    Label levelLabel;
    Label sentenceLabel;

    public Hud(SpriteBatch sb) {
        score = 0;

        viewport = new FitViewport(WordCharger.V_WIDTH, WordCharger.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("1", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        tryDrawChinese("我不能再等了", 64);

        table.add(scoreLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.add(sentenceLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    private void tryDrawChinese(String sentence, int size) {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("DroidSansFallbackFull.ttf"));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = sentence;

        BitmapFont font = gen.generateFont(parameter); // font size in pixels

        gen.dispose();

        sentenceLabel = new Label(parameter.characters, new Label.LabelStyle(font, Color.RED));
    }

    @Override
    public void dispose() {

    }
}
