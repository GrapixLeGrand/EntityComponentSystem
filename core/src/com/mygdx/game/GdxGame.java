package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import game.character.Actor;
import game.character.Constants;
import game.character.GameCharacter;
import game.system.Box2DSingleton;


public class GdxGame extends ApplicationAdapter {

	private OrthographicCamera camera;

	SpriteBatch batch;
	final List<Actor> actors = new ArrayList<Actor>();

	Body ground;


	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight() * Constants.WORLD_TO_BOX;
		camera.viewportWidth = Gdx.graphics.getWidth() * Constants.WORLD_TO_BOX;
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();


		batch = new SpriteBatch();
		actors.add(new GameCharacter("characters/character.png"));

		//static body
		/*
		BodyDef staticBodyDef = new BodyDef();
		staticBodyDef.type = BodyDef.BodyType.StaticBody;
		staticBodyDef.position.set(Vector2.Zero);
		ground = world.createBody(staticBodyDef);
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(1000.0f, sprite.getHeight() * 0.5f);

		FixtureDef groundFixtureDef = new FixtureDef();
		groundFixtureDef.shape = groundShape;
		groundFixtureDef.density = 1f;
		groundFixtureDef.restitution = 0.0f;

		Fixture groundFixture = ground.createFixture(groundFixtureDef);
		groundShape.dispose();
		*/
		//end static body

	}

	@Override
	public void render() {

		camera.update();
		actors.forEach(actor -> actor.update());

		Box2DSingleton.getInstance().world.step(Gdx.graphics.getDeltaTime(), 6, 2);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.getProjectionMatrix().set(camera.combined);
		batch.begin();
		actors.forEach(actor -> actor.render(batch));
		batch.end();
	}

	@Override
	public void dispose() {
		// Hey, I actually did some clean up in a code sample!
		actors.forEach(actor -> actor.dispose());
		Box2DSingleton.getInstance().dispose();
	}
}