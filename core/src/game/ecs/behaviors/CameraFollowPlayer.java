package game.ecs.behaviors;

import com.badlogic.gdx.graphics.OrthographicCamera;

import game.ecs.components.Behavior;
import game.ecs.components.Transform;
import game.ecs.entity.Component;
import game.system.GameInstanceSingleton;

public class CameraFollowPlayer extends Behavior {

    public OrthographicCamera camera;
    public Transform transform;


    @Override
    public void start() {
        camera = GameInstanceSingleton.getInstance().getMainCamera();
        transform = getContainingEntity().getComponent(Transform.class);
    }

    @Override
    public void update(float dt) {
        camera.position.x = transform.getX();
        camera.position.y = transform.getY();
    }

    @Override
    public Component duplicate() {
        return new CameraFollowPlayer();
    }

}
