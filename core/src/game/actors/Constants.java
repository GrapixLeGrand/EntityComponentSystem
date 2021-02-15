package game.actors;

public interface Constants {

    enum PLATFORM { DESKTOP, ANDROID };
    PLATFORM CURRENT_PLATFORM = PLATFORM.DESKTOP;

    float ZOOM_FACTOR = 1.0f;//0.40f;
    float BOX_TO_WORLD = 32.0f;
    float WORLD_TO_BOX = 1.0f / BOX_TO_WORLD;
    float SPRITE_SCALE_UNIT = 20.0f;

    default float BOX_TO_WORLD(float arg) {
        return arg * BOX_TO_WORLD;
    }

    default float WORLD_TO_BOX(float arg) {
        return arg * WORLD_TO_BOX;
    }

}
