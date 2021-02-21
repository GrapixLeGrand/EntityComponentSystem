package game.ecs.behaviors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import game.ecs.components.Behavior;
import game.ecs.components.Rigidbody;
import game.ecs.components.Transform;
import game.ecs.entity.Component;
import game.system.inputs.InputProvider;

public class PlayerMovementsBehavior extends Behavior {

    private Rigidbody rigidbody;

    private Transform entityTransform;

    public float speed = 0.1f;
    public float decreaseFactor = 0.5f;
    public float maxSpeed = 10.0f;

    @Override
    public void start() {
        entityTransform = getContainingEntity().getComponent(Transform.class);
        rigidbody = getContainingEntity().getComponent(Rigidbody.class);
    }

    @Override
    public void update(float dt) {

        Body body = rigidbody.getBody();

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

        entityTransform.setPosition(body.getPosition());

    }

    @Override
    public Component duplicate() {
        return new PlayerMovementsBehavior();
    }
}
