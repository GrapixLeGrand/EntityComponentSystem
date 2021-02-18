package game.ecs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.ecs.components.Behavior;
import game.ecs.components.SpriteRenderer;
import game.ecs.entity.Entity;
import game.ecs.factories.ComponentFactorySingleton;
import game.ecs.pool.ObjectPool;
import game.ecs.pool.ObjectPoolList;

public class EntitiesManagerSingleton {

    private static EntitiesManagerSingleton instance = null;
    private ObjectPool<Entity> entityPool = null;


    private EntitiesManagerSingleton() {
        entityPool = new ObjectPoolList<>(Entity.class);
    }

    public static EntitiesManagerSingleton getInstance() {
        if (instance == null) {
            instance = new EntitiesManagerSingleton();
        }
        return instance;
    }

    public Entity getEntityInstance() {
        return entityPool.getInstance(Entity.class);
    }

    public void releaseEntityInstance(Entity entity) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void startEntitiesBehaviors() {
        Iterator<Entity> entityIterator = entityPool.iterator();

        while (entityIterator.hasNext()) {
            Entity e = entityIterator.next();
            e.startBehaviors();
        }
    }

    public void updateEntitiesBehaviors() {
        Iterator<Entity> entityIterator = entityPool.iterator();

        while (entityIterator.hasNext()) {
            Entity e = entityIterator.next();
            e.updateBehaviors(Gdx.graphics.getDeltaTime());
        }
    }

    public void renderEntities(Batch batch) {

        Iterator<SpriteRenderer> spriteRendererIterator =
                (Iterator<SpriteRenderer>) ComponentFactorySingleton.getInstance().getComponentPool(SpriteRenderer.class).iterator();

        while (spriteRendererIterator.hasNext()) {
            SpriteRenderer spriteRenderer = spriteRendererIterator.next();
            spriteRenderer.render(batch);
        }

    }

}
