package game.ecs.pool;

public interface ComponentPool<T> {
    T query(int id);
    int register(T element);
}
