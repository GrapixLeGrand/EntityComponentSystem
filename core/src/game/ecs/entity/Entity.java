package game.ecs.entity;

import java.util.ArrayList;
import java.util.List;

import game.ecs.Cloneable;
import game.ecs.EntitiesManagerSingleton;
import game.ecs.components.Behavior;

public class Entity implements Cloneable<Entity> {

    private boolean isEnabled = true;
    private String tag;
    private int id;
    private static int counter = 0;
    private List<Component> components;
    private List<Behavior> behaviors;

    public Entity() {
        components = new ArrayList<>();
        behaviors = new ArrayList<>();
        id = counter++;
        tag = "Entity_" + id;
    }

    public void startBehaviors() {
        for (Behavior c : behaviors) {
            c.start();
        }
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
            Behavior behavior = (Behavior)component;
            behaviors.add(behavior);
        }
    }

    public void detachComponent(Component component) {

        Component toBeRemoved = null;
        for (Component c : components) {
            if (component == c) {
                toBeRemoved = c;
                components.remove(c);
            }
        }

        for (Behavior c : behaviors) {
            if (component == c) {
                behaviors.remove(c);
            }
        }

        if (toBeRemoved != null) {
            toBeRemoved.setContainingEntity(null);
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

    public void enable() {
        isEnabled = true;
        startBehaviors();
    }

    public void disable() {
        isEnabled = false;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    @Override
    public Entity duplicate() {
        Entity newInstance = EntitiesManagerSingleton.getInstance().getEntityInstance();

        for (Component c : components) {
            newInstance.attachComponent(c.duplicate());
        }

        for (Behavior b : behaviors) {
            newInstance.attachComponent(b.duplicate());
        }

        newInstance.startBehaviors();
        return newInstance;
    }

}
