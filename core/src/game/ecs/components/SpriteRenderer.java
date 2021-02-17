package game.ecs.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import game.ecs.Component;
import game.ecs.Entity;

public class SpriteRenderer extends Component {

    private Sprite sprite;
    private Texture texture;

    private Transform transform; // reference to the entity transform component

    public SpriteRenderer() {}

    public SpriteRenderer(String imagePath, Transform transform, float width, float height) {
        this(imagePath, 0.0f, 0.0f, width, height);
        this.transform = transform;
    }

    public SpriteRenderer(String imagePath, Vector2 position) {
        this(imagePath, position.x, position.y, 1.0f, 1.0f);
    }

    public SpriteRenderer(String imagePath, Vector2 position, float width, float height) {
        this(imagePath, position.x, position.y, width, height);
    }

    public SpriteRenderer(String imagePath, float x, float y, float width, float height) {
        texture = new Texture(imagePath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setOrigin(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
        sprite.setOriginBasedPosition(game.actors.Constants.WORLD_TO_BOX * 0.5f * x, game.actors.Constants.WORLD_TO_BOX * 0.5f * y);
        transform = null;//getContainingEntity().getComponent(Transform.class);
    }

    public void render(Batch batch) {

        if (transform == null) {
            transform = getContainingEntity().getComponent(Transform.class);
        }

        sprite.setRotation(transform.getRotation() * 180f / (float) Math.PI);
        sprite.setOriginBasedPosition(transform.getX(), transform.getY());
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public static class SpriteRendererBuilder implements ComponentBuilder<SpriteRenderer> {



        @Override
        public SpriteRenderer build(Entity entity) {
            return null;
        }
    }

}
