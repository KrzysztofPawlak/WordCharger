package com.krzych.wordcharger.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Krzysiek on 2017-04-09.
 */

public class WordNote extends InteractiveTileObject {
    public WordNote(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
