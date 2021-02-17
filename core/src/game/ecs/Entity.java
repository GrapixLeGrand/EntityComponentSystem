package game.ecs;

import java.util.ArrayList;
import java.util.List;

import game.ecs.components.Behavior;

public class Entity {

    private int id;
    private static int counter = 0;
    private List<Component> components;
    private List<Behavior> behaviors;

    public Entity() {
        components = new ArrayList<>();
        behaviors = new ArrayList<>();
        id = counter++;
    }

    //private because managed internally
    private void startBehaviors() {
        behaviors.forEach(b -> b.start());
    }

    public void updateBehaviors(float dt) {
        for (Behavior c : behaviors) {
            c.update(dt);
        }
    }

    public int getId() {
        return id;
    }

    private <T> boolean checkRuntimeType(Component check) {
        try {
            T t = (T) check;
        } catch (ClassCastException exp) {
            return false;
        }
        return true;
    }

    public void attachComponent(Component component) {
        if (components.contains(component)) {
            throw new IllegalArgumentException("the component is already registered");
        }
        components.add(component);
        component.setContainingEntity(this);
        if (Behavior.class.isInstance(component)) {
            behaviors.add((Behavior) component);
        }
    }

    public void detachComponent(Component component) {
        //dispose ?
        for (Component c : components) {
            if (component == c) {
                components.remove(c);
            }
        }
    }

    public <T extends Component>  T getComponent(Class<? extends Component> target) {
        for (Component c : components) {
            if (target.isInstance(c)) {
                return (T)c;
            }
        }
        return null;
    }

    public static class EntityBuilder {
        private Entity entity;
        private List<Component> components;
        public EntityBuilder() {
            entity = null;
            components = new ArrayList<>();
        }

        public EntityBuilder withComponent(Component c) {
            components.add(c);
            return this;
        }

        public Entity build() {
            entity = new Entity();
            components.forEach(c -> entity.attachComponent(c));
            entity.startBehaviors();
            return entity;
        }

    }

}
