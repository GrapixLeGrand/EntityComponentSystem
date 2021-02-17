package game.ecs.components;

import game.ecs.entity.Component;

public interface ComponentBuilder<S extends Component> {
    S build();
}
