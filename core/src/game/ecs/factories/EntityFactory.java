package game.ecs.factories;

import com.badlogic.gdx.math.Vector2;
import game.ecs.EntitiesManagerSingleton;
import game.ecs.behaviors.BehaviorTest;
import game.ecs.behaviors.CameraFollowPlayer;
import game.ecs.behaviors.PlayerFaceMouse;
import game.ecs.behaviors.PlayerMovementsBehavior;
import game.ecs.components.Rigidbody;
import game.ecs.components.SpriteRenderer;
import game.ecs.components.Transform;
import game.ecs.entity.Entity;


public class EntityFactory {

    public static Entity createMainCharacter(Vector2 position) {

        Entity entity = EntitiesManagerSingleton.getInstance().getEntityInstance();

        float characterSize = 1.2f;

        Transform.TransformBuilder tb = new Transform.TransformBuilder();
        tb.withPosition(position);
        Transform transform = tb.build();

        SpriteRenderer.SpriteRendererBuilder rendererBuilder = new SpriteRenderer.SpriteRendererBuilder();
        rendererBuilder.withSprite("characters/character.png");
        rendererBuilder.withUniformScale(characterSize);
        rendererBuilder.withCenteredOrigin();
        rendererBuilder.withTransform(transform);
        SpriteRenderer renderer = rendererBuilder.build();

        Rigidbody.RigidbodyBuilder rb = new Rigidbody.RigidbodyBuilder();
        rb.withDynamicBody();
        rb.withPosition(position);
        rb.withBoxShape(renderer.getSprite().getWidth(), renderer.getSprite().getHeight());
        Rigidbody rigidbody = rb.build();

        entity.attachComponent(transform);
        entity.attachComponent(renderer);
        entity.attachComponent(rigidbody);
        entity.attachComponent(new BehaviorTest());
        entity.attachComponent(new PlayerMovementsBehavior());
        entity.attachComponent(new CameraFollowPlayer());
        entity.attachComponent(new PlayerFaceMouse());

        return entity;
    }


}
