package com.krzych.wordcharger.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-04-02.
 */

public class BatteryHero extends Sprite {
    public World world;
    public Body b2dBody;
    private TextureRegion batteryhero;

    public BatteryHero(World world, TextureAtlas at) {
        this.batteryhero = at.findRegion("monsterSpriteSheet");
        this.world = world;

        defineBatteryHero();
        batteryhero = new TextureRegion(batteryhero.getTexture(), 0, 133, 100, 100);
        setBounds(0, 0, 100 / WordCharger.PPM, 100 / WordCharger.PPM);
        setRegion(batteryhero);
    }

    public void update(float dt) {
        setPosition(b2dBody.getPosition().x / 0.7f - getWidth() / 2 , b2dBody.getPosition().y / 0.7f - getHeight() / 2);
    }

    public void defineBatteryHero() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(70 / WordCharger.PPM, 140 / WordCharger.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2dBody = world.createBody(bodyDef); // desc

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35 / WordCharger.PPM);

        fixtureDef.shape = shape;
        b2dBody.createFixture(fixtureDef);
    }
}
