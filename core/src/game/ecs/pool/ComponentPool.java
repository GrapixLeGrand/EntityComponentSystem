package game.ecs.pool;

import com.badlogic.gdx.graphics.Camera;

import game.ecs.entity.Component;

@Deprecated
public interface ComponentPool<T extends Component> {
    T getInstance(Class<? extends Component> target);
}
