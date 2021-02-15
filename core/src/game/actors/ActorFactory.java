package game.actors;

import com.badlogic.gdx.math.Vector2;

public class ActorFactory {

    /*
    private static ActorFactory instance = null;
    private ActorFactory() { }
    public static ActorFactory getInstance() {
        if (instance == null) {
            instance = new ActorFactory();
        }
        return instance;
    }
    */

    public static Actor createBlankObstacle(Vector2 position, float width, float height) {
        return new Obstacle("map/black.png", position, width, height);
    }

    public static Actor createBlankUnitObstacle(Vector2 position) {
        return new Obstacle("map/black.png", position, 1.0f, 1.0f);
    }


}
