package game.ecs;

import java.util.ArrayList;
import java.util.List;

import game.ecs.components.Rigidbody;
import sun.swing.BakedArrayList;

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

    public void update(float dt) {
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
    }

    public void detachComponent(Component component) {
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

    public void test() {
        Rigidbody c = getComponent(Rigidbody.class);
    }

}
