package game.ecs.behaviors;

import game.ecs.components.Behavior;
import game.ecs.entity.Component;

public class BehaviorTest extends Behavior {

    @Override
    public void start() {
        System.out.println("Hello I'm being created !");
    }

    @Override
    public void update(float dt) {
        //System.out.println("Hello I'm being updated !");
    }

    @Override
    public Component duplicate() {
        return new BehaviorTest();
    }
}
