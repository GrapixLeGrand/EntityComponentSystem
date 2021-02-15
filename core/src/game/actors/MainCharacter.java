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
import game.system.inputs.InputProvider;

public class MainCharacter extends Actor {

    private Sprite sprite;
    private Texture img;
    private Body body;
    private float spriteScaleFactor = 1.0f;
    private float speed = 0.1f * Constants.ZOOM_FACTOR;
    private float decreaseFactor = 0.5f * Constants.ZOOM_FACTOR;
    private float maxSpeed = 10.0f;

    public MainCharacter(final String spritePath, final Vector2 position) {

        if (spritePath == null) {
            throw new IllegalArgumentException("sprite path cannot be null");
        }

        img = new Texture(spritePath);
        sprite = new Sprite(img);

        sprite.setSize(img.getWidth() * spriteScaleFactor * Constants.WORLD_TO_BOX,
                img.getHeight() *  spriteScaleFactor * Constants.WORLD_TO_BOX);
        sprite.setOrigin(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
        sprite.setOriginBasedPosition(position.x * game.actors.Constants.WORLD_TO_BOX * 0.5f,
                position.y * game.actors.Constants.WORLD_TO_BOX * 0.5f);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());
        body = Box2DSingleton.getInstance().world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 10.0f;
        fixtureDef.density = -1f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.isSensor = false;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();

    }

    @Override
    public void update() {

        Vector2 mousePosWorld = new Vector2(Gdx.input.getX() * Constants.WORLD_TO_BOX, (Gdx.graphics.getHeight() - Gdx.input.getY()) * Constants.WORLD_TO_BOX);
        Vector2 screenDimsWorld = new Vector2(Gdx.graphics.getWidth() * Constants.WORLD_TO_BOX, Gdx.graphics.getHeight() * Constants.WORLD_TO_BOX);
        mousePosWorld.add(body.getPosition());
        mousePosWorld.sub(screenDimsWorld.scl(0.5f));
        Vector2 playerToMouse = mousePosWorld.sub(body.getPosition()).nor();

        sprite.setRotation(playerToMouse.angleDeg() + 270);

        if (!InputProvider.isForwardActivated() &&
                !InputProvider.isBackwardActivated() &&
                !InputProvider.isLeftActivated() &&
                !InputProvider.isRightActivated() &&
                body.getLinearVelocity().len() < 1e4f) {
            body.setLinearVelocity(0, 0);
        }

        if (body.getLinearVelocity().len() > maxSpeed) {
            Vector2 corrected = body.getLinearVelocity().nor().scl(maxSpeed);
            body.setLinearVelocity(corrected);
        }


        if (InputProvider.isForwardActivated()) {
           body.applyLinearImpulse(new Vector2(0.0f, speed), Vector2.Zero,true);
        } else {
            if (body.getLinearVelocity().dot(Vector2.Y) > 0.0f) {
                body.applyLinearImpulse(new Vector2(0.0f, - speed * decreaseFactor), Vector2.Zero,true);
            }
        }

        if (InputProvider.isBackwardActivated()) {
            body.applyLinearImpulse(new Vector2(0.0f, -speed), Vector2.Zero,true);
        } else {
            if (body.getLinearVelocity().dot(Vector2.Y) < 0.0f) {
                body.applyLinearImpulse(new Vector2(0.0f, speed * decreaseFactor), Vector2.Zero,true);
            }
        }

        if (InputProvider.isLeftActivated()) {
            body.applyLinearImpulse(new Vector2(-speed, 0.0f), Vector2.Zero,true);
        } else {
            if (body.getLinearVelocity().dot(Vector2.X) > 0.0f) {
                body.applyLinearImpulse(new Vector2(speed * decreaseFactor, 0.0f), Vector2.Zero,true);
            }
        }

        if (InputProvider.isRightActivated()) {
            body.applyLinearImpulse(new Vector2(speed, 0.0f), Vector2.Zero,true);
            //body.applyForceToCenter(new Vector2(0.0f, 0.5f), true);
        } else {
            if (body.getLinearVelocity().dot(Vector2.X) < 0.0f) {
                body.applyLinearImpulse(new Vector2(- speed * decreaseFactor, 0.0f), Vector2.Zero,true);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        img.dispose();
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

}
