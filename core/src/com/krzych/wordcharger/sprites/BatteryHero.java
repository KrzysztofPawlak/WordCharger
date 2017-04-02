package com.krzych.wordcharger.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Krzysiek on 2017-04-02.
 */

public class BatteryHero extends Sprite {
    public World world;
    public Body b2dBody;

    public BatteryHero(World world) {
        this.world = world;
        defineBatteryHero();
    }

    public void defineBatteryHero() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(70, 140);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2dBody = world.createBody(bodyDef); // desc

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35);

        fixtureDef.shape = shape;
        b2dBody.createFixture(fixtureDef);
    }
}
