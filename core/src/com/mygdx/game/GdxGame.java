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


public class GdxGame extends ApplicationAdapter {

	public final static float PIXELS_PER_METER = 50.0f;
	public final static float PIXELS_PER_METER_INV = 1.0f / PIXELS_PER_METER;

	public final static float BOX_TO_WORLD = 50.0f;
	public final static float WORLD_TO_BOX = 1.0f / BOX_TO_WORLD;


	private OrthographicCamera camera;

	SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	Body body;

	Body ground;


	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight() * WORLD_TO_BOX;
		camera.viewportWidth = Gdx.graphics.getWidth() * WORLD_TO_BOX;
		camera.position.set(camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 0f);
		camera.update();


		batch = new SpriteBatch();

		img = new Texture("characters/character.png");
		sprite = new Sprite(img);
		final float spriteScaleFactor = 4.0f;
		sprite.setSize(img.getWidth() * spriteScaleFactor * WORLD_TO_BOX,
				img.getHeight() * spriteScaleFactor * WORLD_TO_BOX);

		sprite.setOrigin(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
		//sprite.setOriginCenter();

		sprite.setOriginBasedPosition(Gdx.graphics.getWidth() * WORLD_TO_BOX * 0.5f /** 0.0f*/,
				Gdx.graphics.getHeight() * WORLD_TO_BOX * 0.5f /** 0.0f*/);

		System.out.println(sprite.getOriginX() + " " + sprite.getOriginY());
		System.out.println(sprite.getX() + " " + sprite.getY());
		world = new World(new Vector2(0f, 0f), true);

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


		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		bodyDef.position.set(sprite.getX(), sprite.getY());

		// Create a body in the world using our definition
		body = world.createBody(bodyDef);

		// Now define the dimensions of the physics shape
		PolygonShape shape = new PolygonShape();

		shape.setAsBox(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.friction = 10.0f;
		fixtureDef.density = 1f;
		fixtureDef.restitution = 0.0f;

		Fixture fixture = body.createFixture(fixtureDef);
		shape.dispose();
	}

	@Override
	public void render() {

		camera.update();
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);

		Vector2 mousePos = new Vector2(Gdx.input.getX() * WORLD_TO_BOX, (Gdx.graphics.getHeight() - Gdx.input.getY()) * WORLD_TO_BOX);
		Vector2 playerToMouse = mousePos.sub(body.getPosition()).nor();

		sprite.setRotation(playerToMouse.angleDeg() + 270);

		float speed = 0.4f;
		float decreaseFactor = 0.5f;

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			body.applyLinearImpulse(new Vector2(0.0f, speed), Vector2.Zero,true);
			//body.applyForceToCenter(new Vector2(0.0f, 0.5f), true);
		} else {
			if (body.getLinearVelocity().dot(Vector2.Y) > 0.0f) {
				body.applyLinearImpulse(new Vector2(0.0f, - speed * decreaseFactor), Vector2.Zero,true);
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			body.applyLinearImpulse(new Vector2(0.0f, - speed), Vector2.Zero,true);
			//body.applyForceToCenter(new Vector2(0.0f, 0.5f), true);
		} else {
			if (body.getLinearVelocity().dot(Vector2.Y) < 0.0f) {
				body.applyLinearImpulse(new Vector2(0.0f, speed * decreaseFactor), Vector2.Zero,true);
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			body.applyLinearImpulse(new Vector2(-speed, 0.0f), Vector2.Zero,true);
			//body.applyForceToCenter(new Vector2(0.0f, 0.5f), true);
		} else {
			if (body.getLinearVelocity().dot(Vector2.X) < 0.0f) {
				body.applyLinearImpulse(new Vector2(speed * decreaseFactor, 0.0f), Vector2.Zero,true);
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			body.applyLinearImpulse(new Vector2(speed, 0.0f), Vector2.Zero,true);
			//body.applyForceToCenter(new Vector2(0.0f, 0.5f), true);
		} else {
			if (body.getLinearVelocity().dot(Vector2.X) > 0.0f) {
				body.applyLinearImpulse(new Vector2(- speed * decreaseFactor, 0.0f), Vector2.Zero,true);
			}
		}

		//sprite.setOriginCenter();
		sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.getProjectionMatrix().set(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		// Hey, I actually did some clean up in a code sample!
		img.dispose();
		world.dispose();
	}
}