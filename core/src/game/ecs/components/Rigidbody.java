package game.ecs.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import game.ecs.Component;
import game.system.Box2DSingleton;

public class Rigidbody extends Component {
    private Body body;

    public Rigidbody() {
        body = null;
    }

    public Rigidbody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public static class RigidbodyBuilder {

        private Rigidbody instance;
        private BodyDef bodyDef = new BodyDef();
        private FixtureDef fixtureDef = new FixtureDef();
        private PolygonShape shape = new PolygonShape();

        public RigidbodyBuilder() {
            instance = null;
            fixtureDef.shape = shape;
        }

        public RigidbodyBuilder withBodyType(BodyDef.BodyType type) {
            bodyDef.type = type;
            return this;
        }

        public RigidbodyBuilder withDynamicBody() {
            return withBodyType(BodyDef.BodyType.DynamicBody);
        }

        public RigidbodyBuilder withStaticBody() {
            return withBodyType(BodyDef.BodyType.StaticBody);
        }

        public RigidbodyBuilder withPosition(Vector2 position) {
            bodyDef.position.set(position);
            return this;
        }

        public RigidbodyBuilder withFixtureDef(FixtureDef fixtureDef) {
            this.fixtureDef = fixtureDef;
            return this;
        }

        public RigidbodyBuilder withShape(PolygonShape shape) {
            this.shape = shape;
            return this;
        }

        public RigidbodyBuilder withBoxShape(float hx, float hy) {
            shape.setAsBox(hx, hy);
            return this;
        }

        public Rigidbody build() {
            Body body = Box2DSingleton.getInstance().world.createBody(bodyDef);
            instance = new Rigidbody(body);
            instance.body.createFixture(fixtureDef);
            shape.dispose();
            return instance;
        }
    }

}
