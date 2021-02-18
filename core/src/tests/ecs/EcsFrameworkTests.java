package tests.ecs;

import com.badlogic.gdx.ApplicationAdapter;

import org.junit.Test;

import game.ecs.behaviors.CameraFollowPlayer;
import game.ecs.factories.ComponentFactorySingleton;
import game.ecs.entity.Entity;
import game.ecs.pool.ObjectPool;
import game.ecs.pool.ObjectPoolList;

import static org.junit.Assert.assertEquals;

public class EcsFrameworkTests {

    private FakeGdxGame fakeGame = new FakeGdxGame();


    public class FakeGdxGame extends ApplicationAdapter {

    }


    @Test
    public void test() {
        ObjectPool<CameraFollowPlayer> b = new ObjectPoolList<>(CameraFollowPlayer.class);
    }


    @Test
    public void ObjectPoolTest() {
        ComponentFactorySingleton factory = ComponentFactorySingleton.getInstance();
        Entity e = new Entity();
        TestComponent1 c1 = factory.getInstance(TestComponent1.class);
        e.attachComponent(c1);
    }

    @Test
    public void ComponentFactoryTest() {
        ComponentFactorySingleton factory = ComponentFactorySingleton.getInstance();
        Entity e = new Entity();
        TestComponent1 c1 = factory.getInstance(TestComponent1.class);
        e.attachComponent(c1);
    }

}
