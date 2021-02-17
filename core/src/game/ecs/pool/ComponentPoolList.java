package game.ecs.pool;


import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.Component;

public class ComponentPoolList<T extends Component> implements ComponentPool<T> {

    private List<T> pool;
    private int initialSize = 100;
    private int counter = 0;
    public ComponentPoolList(Class<T> targetComponent) {
        pool = new ArrayList<>(initialSize);
        try {
            for (int i = 0; i < initialSize; i++) {
                T inst = (T) targetComponent.newInstance();
                pool.add(inst);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T query(int index) {
        return pool.get(index);
    }

    @Override
    public int register(T element) {
        if (counter == pool.size() - 1) {
            throw new IllegalArgumentException("cannot add an element to a full pool");
        }
        //pool.set(counter, element); overwrite here...
        int index = counter;
        counter++;
        return index;
    }

}
