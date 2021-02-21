package game.ecs.entity;

import game.ecs.Cloneable;
import game.ecs.Overwriteable;
import game.ecs.entity.Entity;
import game.ecs.factories.ComponentFactorySingleton;

public abstract class Component/*<T>*/ {//implements Cloneable<T> {

    private int ownerId;
    private Entity owner;

    public Entity getContainingEntity() {
        return owner;
    }

    protected void setContainingEntity(Entity entity) {
        owner = entity;
        ownerId = entity.getId();
    }

    public abstract Component duplicate();

}
