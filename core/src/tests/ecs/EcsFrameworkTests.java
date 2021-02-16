package tests.ecs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GdxGame;

import org.junit.BeforeClass;
import org.junit.Test;

import game.ecs.Entity;
import game.ecs.EntityFactory;
import game.ecs.components.Rigidbody;
import game.ecs.components.SpriteRenderer;
import game.ecs.components.Transform;

import static org.junit.Assert.assertEquals;

public class EcsFrameworkTests {

    private FakeGdxGame fakeGame = new FakeGdxGame();


    public class FakeGdxGame extends ApplicationAdapter {

    }


    @Test
    public void test() {
        /*
        Entity entity = EntityFactory.createMainCharacter(Vector2.Zero);
        //assertEquals(true, entity.getComponent(Rigidbody.class) != null);
        assertEquals(true, entity.getComponent(Transform.class) != null);
        //assertEquals(true, entity.getComponent(SpriteRenderer.class) != null);
        entity.updateBehaviors(0.0f);
        */

    }
}
