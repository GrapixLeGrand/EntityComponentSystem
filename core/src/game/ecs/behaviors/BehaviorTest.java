package game.ecs.behaviors;

import game.ecs.components.Behavior;

public class BehaviorTest extends Behavior {
    @Override
    public void start() {
        System.out.println("Hello I'm being created !");
    }

    @Override
    public void update(float dt) {
        System.out.println("Hello I'm being updated !");
    }
}
