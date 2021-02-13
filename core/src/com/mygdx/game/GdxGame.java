package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

import game.actors.Actor;
import game.actors.Constants;
import game.actors.GameCharacter;
import game.actors.Obstacle;
import game.system.Box2DSingleton;


public class GdxGame extends ApplicationAdapter {

	private OrthographicCamera camera;

	SpriteBatch batch;
	final List<Actor> actors = new ArrayList<Actor>();
	TiledMap tiledMap;

	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight() * Constants.WORLD_TO_BOX;
		camera.viewportWidth = Gdx.graphics.getWidth() * Constants.WORLD_TO_BOX;
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();

		batch = new SpriteBatch();
		actors.add(new GameCharacter("characters/character.png"));
		actors.add(new Obstacle("map/black.png"));

		tiledMap = new TmxMapLoader().load("map/simpleLevel.tmx");

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
		actors.forEach(actor -> actor.dispose());
		Box2DSingleton.getInstance().dispose();
	}
}