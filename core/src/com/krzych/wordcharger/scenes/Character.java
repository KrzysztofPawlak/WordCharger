package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    String character;
    Boolean found;

    int x, y;

    public Character(String sendCharacter, Color c, int x, int y) {

        this.x = x;
        this.y = y;

        found = false;
        this.character = sendCharacter;
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("DroidSansFallbackFull.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = sendCharacter;

        font = gen.generateFont(parameter); // font size in pixels
        gen.dispose();

        font.setColor(c);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, character,  x, y);
    }

    public void changeColor(Color c) {
        font.setColor(c);
    }

    public void changeState(boolean state) {
        found = state;
    }

    public Boolean getFound() {
        return found;
    }
}
