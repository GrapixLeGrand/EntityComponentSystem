package game.ecs.behaviors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import game.actors.Constants;
import game.ecs.components.Behavior;
import game.ecs.components.SpriteRenderer;
import game.ecs.components.Transform;
import game.ecs.entity.Component;

public class PlayerFaceMouse extends Behavior {

    private Transform transform;
    private SpriteRenderer renderer;

    @Override
    public void start() {
        transform = getContainingEntity().getComponent(Transform.class);
        renderer = getContainingEntity().getComponent(SpriteRenderer.class);
    }

    @Override
    public void update(float dt) {
        Vector2 mousePosWorld = new Vector2(Gdx.input.getX() * Constants.WORLD_TO_BOX, (Gdx.graphics.getHeight() - Gdx.input.getY()) * Constants.WORLD_TO_BOX);
        Vector2 screenDimsWorld = new Vector2(Gdx.graphics.getWidth() * Constants.WORLD_TO_BOX, Gdx.graphics.getHeight() * Constants.WORLD_TO_BOX);
        mousePosWorld.add(transform.getPosition());
        mousePosWorld.sub(screenDimsWorld.scl(0.5f));
        Vector2 playerToMouse = mousePosWorld.sub(transform.getPosition()).nor();
        transform.setRotation(playerToMouse.angleDeg() + 270);
    }

    @Override
    public Component duplicate() {
        return new PlayerFaceMouse();
    }
}
