package game.ecs.entity;

import game.ecs.entity.Entity;

public abstract class Component {

    private int ownerId;
    private Entity owner;

    public Entity getContainingEntity() {
        return owner;
    }

    protected void setContainingEntity(Entity entity) {
        owner = entity;
        ownerId = entity.getId();
    }


}
