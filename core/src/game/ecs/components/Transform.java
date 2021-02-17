package game.ecs.components;

import com.badlogic.gdx.math.Vector2;

import game.ecs.Component;

public class Transform extends Component {

    private final Vector2 position = Vector2.Zero;
    private float rotation = 0.0f; //radians

    public Transform() {
        this(Vector2.Zero, 0.0f);
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


}
