package com.krzych.wordcharger.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-04-08.
 */

public abstract class InteractiveTileObject {

    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / WordCharger.PPM,
                (bounds.getY() + bounds.getHeight() / 2) / WordCharger.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / WordCharger.PPM, bounds.getHeight() / 2 / WordCharger.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

}
