package com.krzych.wordcharger.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.krzych.wordcharger.WordCharger;

/**
 * Created by Krzysiek on 2017-04-02.
 */

public class BatteryHero extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING}

    public State currentState;
    public State previousState;
    public World world;
    public Body b2dBody;
    private TextureRegion batteryhero;
    private Animation heroRun;
    private Animation heroJump;
    private Animation heroFall;
    private Animation heroStand;

    private float stateTimer;
    private boolean runRight;

    public BatteryHero(World world, TextureAtlas at) {
        this.batteryhero = at.findRegion("monsterSpriteSheet");
        this.world = world;

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runRight = true;

        // run // TODO
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(batteryhero.getTexture(), i * 100, 133, 100, 100));
        }
        heroRun = new Animation(0.1f, frames);
        frames.clear();

        // stand
        heroStand = new Animation(0.1f, new TextureRegion(batteryhero.getTexture(), 0, 133, 100, 100));

        // jump
        heroJump = new Animation(0.1f, new TextureRegion(batteryhero.getTexture(), 6 * 100, 133, 100, 100));

        // fall
        heroFall = new Animation(0.1f, new TextureRegion(batteryhero.getTexture(), 5 * 100, 133, 100, 100));

        defineBatteryHero();
        batteryhero = new TextureRegion(batteryhero.getTexture(), 0, 133, 100, 100);
        setBounds(0, 0, 100 / WordCharger.PPM, 100 / WordCharger.PPM);
        setRegion(batteryhero);
    }

    public void update(float dt) {
        setPosition(b2dBody.getPosition().x / 0.7f - getWidth() / 2, b2dBody.getPosition().y / 0.7f - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case JUMPING:
                region = (TextureRegion) heroJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) heroRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
                region = (TextureRegion) heroFall.getKeyFrame(stateTimer);
                break;
            case STANDING:
            default:
                region = (TextureRegion) heroStand.getKeyFrame(stateTimer);
                break;
        }

        if((b2dBody.getLinearVelocity().x < 0 || !runRight) && !region.isFlipX()) {
            region.flip(true, false);
            runRight = false;
        } else if ((b2dBody.getLinearVelocity().x > 0 || runRight) && region.isFlipX()) {
            region.flip(true, false);
            runRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if (b2dBody.getLinearVelocity().y > 0) {
            return State.JUMPING;
        } else if (b2dBody.getLinearVelocity().y < 0) {
            return State.FALLING;
        } else if (b2dBody.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } else {
            return State.STANDING;
        }
    }

    public void defineBatteryHero() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(70 / WordCharger.PPM, 140 / WordCharger.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2dBody = world.createBody(bodyDef); // desc

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(35 / WordCharger.PPM);

        fixtureDef.filter.categoryBits = WordCharger.BATTERY_HERO_BIT;
        fixtureDef.filter.maskBits = WordCharger.DEFAULT_BIT | WordCharger.WORDNOTE_BIT;

        fixtureDef.shape = shape;

        b2dBody.createFixture(fixtureDef).setUserData("hero");
        fixtureDef.isSensor = true;
    }
}
