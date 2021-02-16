package game.ecs;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class EntitiesManagerSingleton {

    private static EntitiesManagerSingleton instance;
    private List<Entity> entities;

    private EntitiesManagerSingleton() {
        entities = new ArrayList<>();
    }

    public static EntitiesManagerSingleton getInstance() {
        if (instance == null) {
            instance = new EntitiesManagerSingleton();
        }
        return instance;
    }

    public void addEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity cannot be null");
        }
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity cannot be null");
        }
        entities.remove(entity);
    }

    public Entity getEntityById(int id) {
        for (Entity entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    public void updateEntities() {
        for (Entity entity : entities) {
            entity.updateBehaviors(Gdx.graphics.getDeltaTime());
        }
    }

}
