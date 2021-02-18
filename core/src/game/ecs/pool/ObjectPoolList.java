package game.ecs.pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectPoolList<T> implements ObjectPool<T> {

    private enum EntryState {FREE, BUSY}

    private List<T> pool;
    private List<EntryState> occupancy;

    private int size = 100;
    private int counter = 0;

    public ObjectPoolList(Class<T> targetComponent) {
        occupancy = new ArrayList<>(size);
        pool = new ArrayList<>(size);
        try {
            for (int i = 0; i < size; i++) {
                T inst = null;
                inst = (T) targetComponent.newInstance();
                pool.add(inst);
                occupancy.add(EntryState.FREE);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getInstance(Class<?> target) {

        if (counter == size - 1) {
            return null;
        }

        int resultIndex = -1;
        int occupancyIndex = 0;
        for (EntryState s : occupancy) {
            if (s == EntryState.FREE) {
                resultIndex = occupancyIndex;
                counter++;
                break;
            }
            occupancyIndex++;
        }

        if (resultIndex == -1) {
            return null;
        }

        occupancy.set(resultIndex, EntryState.BUSY);
        T inst = pool.get(resultIndex);

        return inst;
    }

    @Override
    public void releaseInstance(T inst) {
        if (inst == null) {
            throw new IllegalArgumentException("instance cannot be null");
        }

        int index = 0;
        for (T t : pool) {
            if (t == pool) { ;
                occupancy.set(index, EntryState.FREE);
                counter--;
                break;
            }
            index++;
        }

    }

    @Override
    public void resize(int size) {
        throw new UnsupportedOperationException("Not implemented !");
    }

    private class PoolIterator implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {

            for (int i = index; i < occupancy.size(); i++) {
                if (occupancy.get(i) == EntryState.BUSY) {
                    index = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            return pool.get(index++);
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new PoolIterator();
    }

}
