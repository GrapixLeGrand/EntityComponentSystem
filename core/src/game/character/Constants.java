package game.character;

public interface Constants {

    float BOX_TO_WORLD = 50.0f;
    float WORLD_TO_BOX = 1.0f / BOX_TO_WORLD;

    default float BOX_TO_WORLD(float arg) {
        return arg * BOX_TO_WORLD;
    }

    default float WORLD_TO_BOX(float arg) {
        return arg * WORLD_TO_BOX;
    }

}
