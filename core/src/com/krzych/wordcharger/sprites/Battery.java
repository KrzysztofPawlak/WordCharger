package com.krzych.wordcharger.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-04-18.
 */

public class Battery extends Sprite {

    private TextureRegion batterylevel;
    private TextureAtlas atlas;

    public Battery(TextureAtlas at) {
        this.atlas = at;
        setBounds(0, 0, 100 / WordCharger.PPM, 50 / WordCharger.PPM);
    }

    public void setOrange() {
        batterylevel = atlas.findRegion("red");
        batterylevel = new TextureRegion(batterylevel.getTexture(), 0, 0, 128, 64);
        setRegion(batterylevel);
    }

    public void setYellow() {
        batterylevel = atlas.findRegion("yellow");
        batterylevel = new TextureRegion(batterylevel.getTexture(), 131, 67, 127, 64);
        setRegion(batterylevel);
    }

    public void setRed() {
        batterylevel = atlas.findRegion("red");
        batterylevel = new TextureRegion(batterylevel.getTexture(), 1, 67, 128, 64);
        setRegion(batterylevel);
    }

    public void setGreen() {
        batterylevel = atlas.findRegion("green");
        batterylevel = new TextureRegion(batterylevel.getTexture(), 703, 169, 127, 64);
        setRegion(batterylevel);
    }

    public void update(BatteryHero hero) {
        setPosition(hero.b2dBody.getPosition().x / 0.7f - getWidth() / 2,
                hero.b2dBody.getPosition().y / 0.7f + getHeight());
    }
}
