package game.ecs;

import com.badlogic.gdx.math.Vector2;

import game.ecs.behaviors.BehaviorTest;
import game.ecs.behaviors.PlayerMovementsBehavior;
import game.ecs.components.Rigidbody;
import game.ecs.components.SpriteRenderer;
import game.ecs.components.Transform;
import game.ecs.entity.Entity;

public class EntityFactory {

    public static Entity createMainCharacter(Vector2 position) {

        Entity entity = new Entity();

        float characterSize = 1.2f;

        Transform t = new Transform(position, 0);

        SpriteRenderer.SpriteRendererBuilder rendererBuilder = new SpriteRenderer.SpriteRendererBuilder();
        rendererBuilder.withSprite("characters/character.png");
        rendererBuilder.withTextureDims();
        rendererBuilder.withUniformScale(characterSize);
        rendererBuilder.withCenteredOrigin();
        SpriteRenderer renderer = rendererBuilder.build();


        Rigidbody.RigidbodyBuilder rb = new Rigidbody.RigidbodyBuilder();
        rb.withDynamicBody();
        rb.withPosition(position);
        rb.withBoxShape(renderer.getTexture().getWidth(), renderer.getTexture().getHeight());
        Rigidbody rigidbody = rb.build();

        BehaviorTest bt = new BehaviorTest();
        PlayerMovementsBehavior pmb = new PlayerMovementsBehavior();

        entity.attachComponent(renderer);
        entity.attachComponent(rigidbody);
        entity.attachComponent(bt);
        entity.attachComponent(pmb);
        EntitiesManagerSingleton.getInstance().addEntity(entity);

        return entity;
    }


}
