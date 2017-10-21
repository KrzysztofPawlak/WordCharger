package com.krzych.wordcharger.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.krzych.wordcharger.WordCharger;

import java.util.LinkedList;

/**
 * Created by Krzysiek on 2017-10-19.
 */

public class Sentence implements Disposable{

    public Stage stage;
    private Viewport viewport;

    Character ch, ch2, ch3, ch4, ch5, ch6;
    String sentence = "我不能再等了";

    OrthographicCamera cam;

    Batch sb;

    public Sentence(SpriteBatch sb) {
        this.sb = sb;

        cam = new OrthographicCamera();
        viewport = new FitViewport(800, 480, cam);
        stage = new Stage(viewport, WordCharger.batch);

        ch = new Character("我", Color.CYAN, 300, 450);
        ch2 = new Character("不", Color.CYAN, 300 + 64 * 1, 450);
        ch3 = new Character("能", Color.CYAN, 300 + 64 * 2, 450);
        ch4 = new Character("再", Color.CYAN, 300 + 64 * 3, 450);
        ch5 = new Character("等", Color.CYAN, 300 + 64 * 4, 450);
        ch6 = new Character("了", Color.CYAN, 300 + 64 * 5, 450);

        LinkedList<Character> mapCharacter = new LinkedList<Character>();
        mapCharacter.add(ch);
        mapCharacter.add(ch2);
        mapCharacter.add(ch3);
        mapCharacter.add(ch4);
        mapCharacter.add(ch5);
        mapCharacter.add(ch6);

        stage.addActor(ch);
        stage.addActor(ch2);
        stage.addActor(ch3);
        stage.addActor(ch4);
        stage.addActor(ch5);
        stage.addActor(ch6);

        ch.changeState(true);

        for (Character obj: mapCharacter) {
            if(obj.getFound())
                obj.changeColor(Color.BROWN);
        }

        System.out.println("size: " + stage.getActors().size);
    }

    public void draw() {
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
