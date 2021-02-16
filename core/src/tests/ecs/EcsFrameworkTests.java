package tests.ecs;

import org.junit.Test;

import game.ecs.Entity;
import game.ecs.components.Rigidbody;
import game.ecs.components.SpriteRenderer;
import game.ecs.components.Transform;

import static org.junit.Assert.assertEquals;

public class EcsFrameworkTests {
    @Test
    public void test() {
        Entity entity = new Entity();
        Rigidbody r = new Rigidbody();
        Transform t = new Transform();
        entity.attachComponent(r);
        entity.attachComponent(t);
        assertEquals(r, entity.getComponent(Rigidbody.class));
        assertEquals(t, entity.getComponent(Transform.class));
        assertEquals(null, entity.getComponent(SpriteRenderer.class));
    }
}
