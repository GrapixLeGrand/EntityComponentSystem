package game.ecs.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import game.ecs.factories.ComponentFactorySingleton;
import game.ecs.entity.Component;

public class SpriteRenderer extends Component {

    private Texture texture = new Texture("map/black.png");
    private Sprite sprite = new Sprite(texture);
    private Transform transform = new Transform();
    private boolean isEnabled = true;

    public SpriteRenderer() {
        sprite.setSize(1.0f, 1.0f);
        sprite.setOrigin(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
    }

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
        transform = null;
    }

    public void render(Batch batch) {
        if (isEnabled) {
            if (transform == null) {
                transform = getContainingEntity().getComponent(Transform.class);
            }

            sprite.setRotation(transform.getRotation());
            sprite.setOriginBasedPosition(transform.getX(), transform.getY()); //same referential as box2d
            sprite.draw(batch);
        }
    }

    public void enable() {
        isEnabled = true;
    }

    public void disable() {
        isEnabled = false;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public static class SpriteRendererBuilder implements ComponentBuilder<SpriteRenderer> {

        private Texture texture = new Texture("map/black.png");
        private Sprite sprite = new Sprite(texture);
        private SpriteRenderer instance = null;
        private Transform transform = new Transform();
        private float width = 1.0f;
        private float height = 1.0f;

        public SpriteRendererBuilder withCenteredOrigin() {
            sprite.setSize(width, height);
            sprite.setOrigin(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
            return this;
        }

        public SpriteRendererBuilder withTextureDims() {
            return withDims(texture.getWidth(), texture.getHeight());
        }

        public SpriteRendererBuilder withDims(float width, float height) {
            this.width = width;
            this.height = height;
            sprite.setSize(width, height);
            return this;
        }

        public SpriteRendererBuilder withUniformScale(float scale) {
            float maxDim = Math.max(texture.getWidth(), texture.getHeight());
            float minDim = Math.min(texture.getWidth(), texture.getHeight());
            float imgRatio = minDim / maxDim;
            sprite.setSize(1.0f * scale, 1.0f * imgRatio * scale);
            return this;
        }

        public SpriteRendererBuilder withSprite(String imagePath) {
            this.texture = new Texture(imagePath);
            this.sprite = new Sprite(texture);
            return this;
        }

        public SpriteRendererBuilder withSprite(Sprite sprite) {
            this.sprite = sprite;
            return this;
        }

        public SpriteRendererBuilder withTexture(Texture texture) {
            this.texture = texture;
            return this;
        }

        public SpriteRendererBuilder withTransform(Transform transform) {
            this.transform = transform;
            return this;
        }

        @Override
        public SpriteRenderer build() {
            instance = ComponentFactorySingleton.getInstance().getInstance(SpriteRenderer.class); //new SpriteRenderer();
            instance.setSprite(sprite);
            instance.setTexture(texture);
            instance.setTransform(transform);
            return instance;
        }
    }

}
