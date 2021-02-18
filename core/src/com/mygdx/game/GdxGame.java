package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

import game.actors.Actor;
import game.actors.Constants;
import game.actors.MainCharacter;
import game.actors.Obstacle;
import game.ecs.EntitiesManagerSingleton;
import game.ecs.factories.EntityFactory;
import game.maps.PhysicalTilesContainer;
import game.system.Box2DSingleton;
import game.system.GameInstanceSingleton;

public class GdxGame extends ApplicationAdapter {

	private EntitiesManagerSingleton entitiesManager = EntitiesManagerSingleton.getInstance();
	private OrthographicCamera camera;
	private GameInstanceSingleton gameInstance = GameInstanceSingleton.getInstance();
	private ScreenViewport viewport;

	SpriteBatch batch;
	final List<Actor> actors = new ArrayList<Actor>();
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	private Vector2 mainCharacterPosition = new Vector2(0.0f, 0.0f);
	private MainCharacter mainCharacter;
	private int maxHorizontalTiles = 12;

	@Override
	public void create() {

		mainCharacter =
			new MainCharacter("characters/character.png", mainCharacterPosition);
		camera = new OrthographicCamera();//new FollowingOrthographicCamera(mainCharacter);
		viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(Constants.WORLD_TO_BOX);
		float screenRatio = Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() * 1.0f);
		camera.setToOrtho(false, maxHorizontalTiles, maxHorizontalTiles * screenRatio);
		viewport.setCamera(camera);

		batch = new SpriteBatch();
		actors.add(mainCharacter);
		actors.add(new Obstacle("map/black.png", Vector2.Zero, 1.0f, 1.0f));

		tiledMap = new TmxMapLoader().load("map/simplelevel.xml");
		PhysicalTilesContainer physicalTilesContainer = new PhysicalTilesContainer(tiledMap);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, Constants.WORLD_TO_BOX);

		gameInstance.setMainCamera(camera);
		gameInstance.setMainCharacter(mainCharacter);

		EntityFactory.createMainCharacter(mainCharacterPosition);
		entitiesManager.startEntitiesBehaviors();
	}

	@Override
	public void render() {

		//System.out.println(mainCharacter.getPosition());

		camera.update();
		entitiesManager.updateEntitiesBehaviors();
		//actors.forEach(actor -> actor.update());

		Box2DSingleton.getInstance().world.step(Gdx.graphics.getDeltaTime(), 6, 2);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.getProjectionMatrix().set(camera.combined);
		camera.zoom = Constants.ZOOM_FACTOR;

		batch.begin();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		//actors.forEach(actor -> actor.render(batch));
		entitiesManager.renderEntities(batch);
		batch.end();

	}

	@Override
	public void dispose() {
		actors.forEach(actor -> actor.dispose());
		Box2DSingleton.getInstance().dispose();
	}
}