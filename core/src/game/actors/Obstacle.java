package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import game.system.Box2DSingleton;

public class Obstacle extends Actor {

    private Sprite sprite;
    private Texture img;
    private Body body;
    private float spriteScaleFactor = Constants.ZOOM_FACTOR;

    public Obstacle(String spritePath, Vector2 position, float width, float height) {
        if (spritePath == null) {
            throw new IllegalArgumentException("sprite path cannot be null");
        }

        img = new Texture(spritePath);
        sprite = new Sprite(img);

        //sprite.setSize(Constants.SPRITE_SCALE_UNIT * width * Constants.WORLD_TO_BOX, Constants.SPRITE_SCALE_UNIT * height * Constants.WORLD_TO_BOX);
        sprite.setSize(width, height);
        sprite.setOrigin(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
        //sprite.setOriginBasedPosition(Gdx.graphics.getWidth() * game.actors.Constants.WORLD_TO_BOX * 0.5f,
         //       Gdx.graphics.getHeight() * game.actors.Constants.WORLD_TO_BOX * 0.5f);

        sprite.setOriginBasedPosition(position.x, position.y);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position);
        body = Box2DSingleton.getInstance().world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 10.0f;
        fixtureDef.density = 0f;
        fixtureDef.restitution = -1.0f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
