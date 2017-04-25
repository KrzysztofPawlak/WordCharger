package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-04-25.
 */

public class Controller {
    Viewport viewport;
    Stage stage;
    boolean upPressed, leftPressed, rightPressed;
    OrthographicCamera cam;

    public Controller() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(800, 480, cam);
        stage = new Stage(viewport, WordCharger.batch);
        Gdx.input.setInputProcessor(stage);

        Table tableLeftRight = new Table();
        tableLeftRight.left().bottom();
        tableLeftRight.setFillParent(true);

        Table tableJump = new Table();
        tableJump.right().bottom();
        tableJump.setFillParent(true);

        Image controlsLeftImage = new Image(new Texture("controlLeft.png"));
        controlsLeftImage.setSize(96, 96);
        controlsLeftImage.addListener(new InputListener() { // Overitemethod
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        }); //TODO kropkę

        Image controlsRightImage = new Image(new Texture("controlRight.png"));
        controlsRightImage.setSize(96, 96);
        controlsRightImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        Image controlsUpImage = new Image(new Texture("controlUp.png"));
        controlsUpImage.setSize(96, 96);
        controlsUpImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });

        tableLeftRight.padBottom(10);
        tableLeftRight.add().pad(10);
        tableLeftRight.add(controlsLeftImage).size(controlsLeftImage.getWidth(), controlsLeftImage.getHeight());
        tableLeftRight.add().pad(20);
        tableLeftRight.add(controlsRightImage).size(controlsRightImage.getWidth(), controlsRightImage.getHeight());
        tableLeftRight.setDebug(true);
        stage.addActor(tableLeftRight);

        tableJump.padBottom(10);
        tableJump.add(controlsUpImage).size(controlsUpImage.getWidth(), controlsUpImage.getHeight());
        tableJump.add().pad(10);
        tableJump.setDebug(true);
        stage.addActor(tableJump);
    }

    public void draw() {
        stage.draw();
    }

    // TODO generate getter
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
}
