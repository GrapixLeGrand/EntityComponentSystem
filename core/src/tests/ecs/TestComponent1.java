package tests.ecs;

import game.ecs.entity.Component;

public class TestComponent1 extends Component {
    private int dummy = 1;
    public TestComponent1() {
        dummy = 0;
    }
    public int getDummy() {
        return dummy;
    }

    @Override
    public TestComponent1 duplicate() {
        return null;
    }
}
