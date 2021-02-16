package game.ecs;

import com.badlogic.gdx.math.Vector2;

import game.ecs.components.Rigidbody;
import game.ecs.components.SpriteRenderer;

public class EntityFactory {

    public Entity createMainCharacter(Vector2 position) {

        float characterSize = 1.2f;

        Entity e = new Entity();

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

        return null;
    }


}
