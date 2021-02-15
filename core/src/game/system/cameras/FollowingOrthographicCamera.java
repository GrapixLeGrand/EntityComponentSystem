package game.system.cameras;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import game.actors.MainCharacter;

public class FollowingOrthographicCamera extends OrthographicCamera {

    private MainCharacter character;

    public FollowingOrthographicCamera(MainCharacter character) {
        if (character == null) {
            throw new IllegalArgumentException("character cannot be null");
        }
        this.character = character;
    }

    private void updateCameraPosition() {
        Vector2 position = character.getPosition();
        this.position.x = position.x;
        this.position.y = position.y;
    }

    @Override
    public void update() {
        updateCameraPosition();
        super.update();
    }

    @Override
    public void update(boolean updateFrustum) {
        updateCameraPosition();
        super.update(updateFrustum);
    }

}
