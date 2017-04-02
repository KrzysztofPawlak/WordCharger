package com.krzych.wordcharger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;
import com.krzych.wordcharger.scenes.Hud;
import com.krzych.wordcharger.sprites.BatteryHero;

/**
 * Created by Krzysiek on 2017-03-14.
 */

public class PlayScreen implements Screen {

    private WordCharger game;
    Texture img;
    private OrthographicCamera camera;
    private Viewport view;
    private Hud hud;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private BatteryHero player; // desc

    public PlayScreen(WordCharger game) {
        this.game = game;
        img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera();
        view = new FitViewport(800, 480, camera); // TODO more universal

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("untitled.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 1.3f);

        camera.position.set(view.getWorldWidth() / 2, view.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);

        player = new BatteryHero(world); // desc

        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2,
                    rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        handleInput(dt);

//        world.step(1 / 60f, 6, 2); // desc

        camera.update();
    }

    public void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x += 100 * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x -= 100 * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y += 100 * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y -= 100 * dt;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        renderer.render();
        renderer.setView(camera);

        b2dr.render(world, camera.combined.scale(1 / 1.3f, 1 / 1.3f, 0));
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
