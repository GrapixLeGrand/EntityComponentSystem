package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import game.actors.Actor;
import game.actors.ActorFactory;
import game.actors.Constants;
import game.actors.MainCharacter;
import game.actors.Obstacle;
import game.system.Box2DSingleton;
import game.system.GameInstanceSingleton;
import game.system.cameras.FollowingOrthographicCamera;


public class GdxGame extends ApplicationAdapter {

	private OrthographicCamera camera;
	private GameInstanceSingleton gameInstance = GameInstanceSingleton.getInstance();

	SpriteBatch batch;
	final List<Actor> actors = new ArrayList<Actor>();
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	private Vector2 mainCharacterPosition = new Vector2(0.0f, 0.0f);
	private MainCharacter mainCharacter;
	private final float mapFactor = 1.5f;

	@Override
	public void create() {

		mainCharacter =
			new MainCharacter("characters/character.png", mainCharacterPosition);
		camera = new FollowingOrthographicCamera(mainCharacter);
		camera.viewportHeight = Gdx.graphics.getHeight() * Constants.WORLD_TO_BOX;
		camera.viewportWidth = Gdx.graphics.getWidth() * Constants.WORLD_TO_BOX;
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();

		batch = new SpriteBatch();
		actors.add(mainCharacter);
		actors.add(new Obstacle("map/black.png", Vector2.Zero, 1.0f, 1.0f));

		tiledMap = new TmxMapLoader().load("map/simpleLevel.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, Constants.WORLD_TO_BOX * mapFactor * Constants.ZOOM_FACTOR);
		System.out.println(tiledMap.getTileSets());


		HashMap<Integer, String> idToSetName = new HashMap<>();
		List<String> physicalTileSetsNames = Arrays.asList("Brick");
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		Vector2 layerOffset = new Vector2(layer.getOffsetX(), layer.getOffsetY());
		Vector2 layerRenderOffset = new Vector2(layer.getRenderOffsetX(), layer.getRenderOffsetY());
		Iterator<TiledMapTileSet> tileSetsIterator = tiledMap.getTileSets().iterator();
		while (tileSetsIterator.hasNext()) {
			TiledMapTileSet current = tileSetsIterator.next();
			//System.out.println(current.getName());
			Iterator<TiledMapTile> tilesIterator = current.iterator();
			while (tilesIterator.hasNext()) {
				TiledMapTile tile = tilesIterator.next();
				idToSetName.put(tile.getId(), current.getName());
			}
		}

		List<TiledMapTileLayer.Cell> layers = new ArrayList<TiledMapTileLayer.Cell>();


		for (int x = 0; x < layer.getWidth(); x ++) {
			for (int y = 0; y < layer.getHeight(); y ++) {
				TiledMapTile currentTile = layer.getCell(x, y).getTile();
				String tileSetName = idToSetName.get(currentTile.getId());
				if (physicalTileSetsNames.contains(tileSetName)) {


					Body body = null;
					Vector2 tilePosition = new Vector2(x, y);
					Vector2 tileWorldPosition = tilePosition;//.scl(mapFactor * Constants.WORLD_TO_BOX);//.scl(Constants.WORLD_TO_BOX * mapFactor);

					//Vector2 tileTest = tilePosition.scl(1);

					BodyDef bodyDef = new BodyDef();
					bodyDef.type = BodyDef.BodyType.StaticBody;
					bodyDef.position.set(tileWorldPosition);

					/*
					body = Box2DSingleton.getInstance().world.createBody(bodyDef);

					PolygonShape shape = new PolygonShape();

					shape.setAsBox(Constants.WORLD_TO_BOX * mapFactor, Constants.WORLD_TO_BOX * mapFactor);
					FixtureDef fixtureDef = new FixtureDef();
					fixtureDef.shape = shape;
					fixtureDef.friction = 10.0f;
					fixtureDef.density = 0f;
					fixtureDef.restitution = -1.0f;

					Fixture fixture = body.createFixture(fixtureDef);
					shape.dispose();
					 */
					//.add(layerRenderOffset)
					Actor a = ActorFactory.createBlankObstacle(tileWorldPosition, 1.0f, 1.0f);
					actors.add(a);
				}
			}
		}

		gameInstance.setMainCamera(camera);
		gameInstance.setMainCharacter(mainCharacter);

	}

	@Override
	public void render() {

		camera.update();
		actors.forEach(actor -> actor.update());

		Box2DSingleton.getInstance().world.step(Gdx.graphics.getDeltaTime(), 6, 2);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.getProjectionMatrix().set(camera.combined);
		camera.zoom = Constants.ZOOM_FACTOR;
		//camera.position.x = 0;
		//camera.position.y = 0;

		batch.begin();
		tiledMapRenderer.setView((OrthographicCamera) camera);
		tiledMapRenderer.render();
		actors.forEach(actor -> actor.render(batch));
		batch.end();

	}

	@Override
	public void dispose() {
		actors.forEach(actor -> actor.dispose());
		Box2DSingleton.getInstance().dispose();
	}
}