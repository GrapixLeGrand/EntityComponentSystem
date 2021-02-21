package game.ecs;

public interface Overwriteable<T> {
    T overwrite(T instance);
}
