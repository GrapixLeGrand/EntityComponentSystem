package game.ecs.pool;

import java.util.Iterator;

public interface ObjectPool<T> {
    T getInstance(Class<?> target);
    void releaseInstance(T inst);
    void resize(int size);
    Iterator<T> iterator();
}
