package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Krzysiek on 2017-10-19.
 */

public class Character extends Actor {

    BitmapFont font;
    int size = 64;
    String sentence = "我不能再等了";

    public Character() {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("DroidSansFallbackFull.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = sentence;

        font = gen.generateFont(parameter); // font size in pixels
        gen.dispose();

        font.setColor(0.5f,0.4f,0,1);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, sentence, 300, 450);
    }
}
