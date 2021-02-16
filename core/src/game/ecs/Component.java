package game.ecs;

public abstract class Component {
    private int ownerId;
    private Entity owner;

    public Entity getContainingEntity() {
        return owner;
    }

    protected void setContainingEntity(Entity entity) {
        owner = entity;
    }

    public int getContainingEntityId() {
        return ownerId;
    }

    protected void setContainingEntityId(int id) {
        ownerId = id;
    }

}
