package game.ecs.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import game.ecs.factories.ComponentFactorySingleton;
import game.ecs.entity.Component;
import game.system.Box2DSingleton;

public class Rigidbody extends Component {
    private Body body;
    private Transform transform;

    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private PolygonShape shape;

    public Rigidbody() {
        body = null;
    }

    public Rigidbody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public Rigidbody duplicate() {
        Rigidbody newInstance = ComponentFactorySingleton.getInstance().getInstance(Rigidbody.class);
        Body body = Box2DSingleton.getInstance().world.createBody(bodyDef);
        newInstance = ComponentFactorySingleton.getInstance().getInstance(Rigidbody.class); //new Rigidbody();
        newInstance.setBody(body);
        newInstance.bodyDef = bodyDef;
        newInstance.shape = shape;
        newInstance.fixtureDef = fixtureDef;
        newInstance.body.createFixture(fixtureDef);
        return newInstance;
    }

    public static class RigidbodyBuilder implements ComponentBuilder<Rigidbody> {

        private Rigidbody instance;
        private BodyDef bodyDef;
        private FixtureDef fixtureDef;
        private PolygonShape shape;

        public RigidbodyBuilder() {
            Box2DSingleton inst = Box2DSingleton.getInstance(); // need to create the world by forcing and instance creation for the polygon
            bodyDef = new BodyDef();
            fixtureDef = new FixtureDef();
            shape = new PolygonShape();
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
            shape.setAsBox(hx * 0.5f, hy * 0.5f);
            return this;
        }

        @Override
        public Rigidbody build() {
            Body body = Box2DSingleton.getInstance().world.createBody(bodyDef);
            instance = ComponentFactorySingleton.getInstance().getInstance(Rigidbody.class); //new Rigidbody();
            instance.setBody(body);
            instance.bodyDef = bodyDef;
            instance.shape = shape;
            instance.fixtureDef = fixtureDef;
            instance.body.createFixture(fixtureDef);
            //shape.dispose();
            return instance;
        }

    }

}
