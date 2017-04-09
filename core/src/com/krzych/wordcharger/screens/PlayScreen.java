package com.krzych.wordcharger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.Tools.B2WorldCreator;
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
        view = new FitViewport(WordCharger.V_WIDTH / WordCharger.PPM, WordCharger.V_HEIGHT / WordCharger.PPM, camera);

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("untitled.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 1.3f * (1 / WordCharger.PPM));

        camera.position.set(view.getWorldWidth() / 2, view.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);

        player = new BatteryHero(world);

        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);
    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        camera.position.x = player.b2dBody.getPosition().x / 1.3f;
        camera.position.y = player.b2dBody.getPosition().y / 1.3f;

        camera.update();

//        renderer.setView(camera);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && player.b2dBody.getLinearVelocity().x <= 2) {
            player.b2dBody.applyLinearImpulse(new Vector2(2f, 0), player.b2dBody.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && player.b2dBody.getLinearVelocity().x >= -2) {
            player.b2dBody.applyLinearImpulse(new Vector2(-2f, 0), player.b2dBody.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.b2dBody.applyLinearImpulse(new Vector2(0, 5f), player.b2dBody.getWorldCenter(), true);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
