package game.ecs.components;

import game.ecs.Component;
import game.ecs.Entity;

public interface ComponentBuilder<S extends Component> {
    S build(Entity entity);
}
