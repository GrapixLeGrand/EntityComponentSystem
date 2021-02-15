package game.ecs;

import java.util.List;

public class Entity {
    private int id;
    private static int counter = 0;
    private List<Component> components;
    public Entity() {
        id = counter++;
    }
}
