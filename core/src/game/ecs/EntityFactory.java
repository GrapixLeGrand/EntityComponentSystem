package game.ecs;

import com.badlogic.gdx.math.Vector2;

import game.ecs.behaviors.BehaviorTest;
import game.ecs.behaviors.PlayerMovementsBehavior;
import game.ecs.components.Rigidbody;
import game.ecs.components.SpriteRenderer;
import game.ecs.components.Transform;

public class EntityFactory {

    public static Entity createMainCharacter(Vector2 position) {

        Entity entity = new Entity();

        float characterSize = 1.2f;

        Transform t = new Transform(position, 0);


        SpriteRenderer sr =
                new SpriteRenderer("characters/character.png", position);

        float maxDim = Math.max(sr.getTexture().getWidth(), sr.getTexture().getHeight());
        float minDim = Math.min(sr.getTexture().getWidth(), sr.getTexture().getHeight());
        float imgRatio = minDim / maxDim;
        sr.getSprite().setSize(1.0f * characterSize, 1.0f * imgRatio * characterSize);


        Rigidbody.RigidbodyBuilder rb = new Rigidbody.RigidbodyBuilder();
        rb.withDynamicBody();
        rb.withPosition(position);
        rb.withBoxShape(sr.getTexture().getWidth(), sr.getTexture().getHeight());
        Rigidbody r = rb.build();

        BehaviorTest bt = new BehaviorTest();
        PlayerMovementsBehavior pmb = new PlayerMovementsBehavior();

        /*
        Entity.EntityBuilder eb = new Entity.EntityBuilder();
        eb.withComponent(t);
        eb.withComponent(sr);
        eb.withComponent(r);
        eb.withComponent(bt);
        eb.withComponent(pmb);
        Entity e = eb.build();
        */
        EntitiesManagerSingleton.getInstance().addEntity(entity);
        return entity;
    }


}
