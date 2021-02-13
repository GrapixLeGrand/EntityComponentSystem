package game.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DSingleton {

    private static Box2DSingleton instance = null;
    private Vector2 gravity = Vector2.Zero; //in our current game
    public World world;

    private Box2DSingleton() {
        world = new World(gravity, true);
    }

    public static Box2DSingleton getInstance() {
        if (instance == null) {
            instance = new Box2DSingleton();
        }
        return instance;
    }

    public void dispose() {
        world.dispose();
    }

}
