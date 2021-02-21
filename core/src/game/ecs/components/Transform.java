package game.ecs.components;

import com.badlogic.gdx.math.Vector2;

import game.ecs.factories.ComponentFactorySingleton;
import game.ecs.entity.Component;

public class Transform extends Component {

    private final Vector2 position = new Vector2(0, 0);
    private float rotation = 0.0f; //radians

    public Transform() {
        this(new Vector2(0, 0), 0.0f);
    }

    public Transform(Vector2 position) {
        this(position.x, position.y);
    }

    public Transform(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public Transform(Vector2 position, float rotation) {
        this(position.x, position.y, rotation);
    }

    public Transform(float x, float y, float rotation) {
        position.x = x;
        position.y = y;
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setRotation(float orientation) {
        this.rotation = orientation;
    }

    public void setPosition(final Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    @Override
    public Transform duplicate() {
        Transform newTransform = ComponentFactorySingleton.getInstance().getInstance(Transform.class);
        newTransform.setPosition(this.position);
        newTransform.setRotation(this.rotation);
        return newTransform;
    }

    public static class TransformBuilder implements ComponentBuilder<Transform> {

        private Transform instance = null;
        private Vector2 position = null;
        private float orientation = 0.0f;

        public TransformBuilder() {
            position = new Vector2(0, 0);
            position.x = 0;
            position.y = 0;
            orientation = 0.0f;
        }

        public TransformBuilder withPosition(final Vector2 position) {
            this.position.x = position.x;
            this.position.y = position.y;
            return this;
        }

        public TransformBuilder withOrientation(float orientation) {
            this.orientation = orientation;
            return this;
        }

        @Override
        public Transform build() {
            instance = ComponentFactorySingleton.getInstance().getInstance(Transform.class);
            instance.setPosition(position);
            instance.setRotation(orientation);
            return instance;
        }
    }

}
