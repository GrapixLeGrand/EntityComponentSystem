package game.actors;

public interface Constants {

    enum PLATFORM { DESKTOP, ANDROID };
    PLATFORM CURRENT_PLATFORM = PLATFORM.DESKTOP;

    float ZOOM_FACTOR = 4.0f;
    float BOX_TO_WORLD = 40.0f;
    float WORLD_TO_BOX = 1.0f / BOX_TO_WORLD;

    default float BOX_TO_WORLD(float arg) {
        return arg * BOX_TO_WORLD;
    }

    default float WORLD_TO_BOX(float arg) {
        return arg * WORLD_TO_BOX;
    }

}
