package game.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Actor {
    abstract public void update();
    abstract public void render(SpriteBatch batch);
    abstract public void dispose();
}
