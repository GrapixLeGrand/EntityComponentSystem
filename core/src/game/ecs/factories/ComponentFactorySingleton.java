package game.ecs.factories;

import java.util.HashMap;

import game.ecs.entity.Component;
import game.ecs.pool.ComponentPool;
import game.ecs.pool.ComponentPoolList;
import game.ecs.pool.ObjectPool;
import game.ecs.pool.ObjectPoolList;

public class ComponentFactorySingleton {

    private static ComponentFactorySingleton instance = null;
    private HashMap<Class<? extends Component>, ObjectPool<? extends Component>> pools;

    private ComponentFactorySingleton() {
        pools = new HashMap<>();
    }

    public static ComponentFactorySingleton getInstance() {
        if (instance == null) {
            instance = new ComponentFactorySingleton();
        }
        return instance;
    }

    public <T extends Component> T getInstance(Class<T> component) {

        ObjectPool<? extends Component> pool = pools.getOrDefault(component, null);
        if (pool == null) {
            pool = new ObjectPoolList<>(component);
            pools.put(component, pool);
        }

        return (T) pool.getInstance(component);
    }

    public <T extends Component> ObjectPool<? extends Component> getComponentPool(Class<T> component) {
        ObjectPool<? extends Component> pool = pools.getOrDefault(component, null);
        if (pool == null) {
            pool = new ObjectPoolList<>(component);
            pools.put(component, pool);
        }
        return pool;
    }

}
