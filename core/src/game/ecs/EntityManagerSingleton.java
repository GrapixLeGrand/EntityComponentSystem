package game.ecs;

import java.util.ArrayList;
import java.util.List;

public class EntityManagerSingleton {

    private static EntityManagerSingleton instance;
    private List<Entity> entities;

    private EntityManagerSingleton() {
        entities = new ArrayList<>();
    }

    public static EntityManagerSingleton getInstance() {
        if (instance == null) {
            instance = new EntityManagerSingleton();
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

}
