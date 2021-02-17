package tests.ecs;

import com.badlogic.gdx.ApplicationAdapter;

import org.junit.Test;

import game.ecs.components.Transform;
import game.ecs.pool.ComponentPoolList;

import static org.junit.Assert.assertEquals;

public class EcsFrameworkTests {

    private FakeGdxGame fakeGame = new FakeGdxGame();


    public class FakeGdxGame extends ApplicationAdapter {

    }


    @Test
    public void test() {
        ComponentPoolList<Transform> pool = new ComponentPoolList<>(Transform.class);

        /*
        Entity entity = EntityFactory.createMainCharacter(Vector2.Zero);
        //assertEquals(true, entity.getComponent(Rigidbody.class) != null);
        assertEquals(true, entity.getComponent(Transform.class) != null);
        //assertEquals(true, entity.getComponent(SpriteRenderer.class) != null);
        entity.updateBehaviors(0.0f);
        */

    }
}
