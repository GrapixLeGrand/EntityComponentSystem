package game.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import game.actors.MainCharacter;

public class GameInstanceSingleton {

    private OrthographicCamera mainCamera;
    private MainCharacter mainCharacter;

    private static GameInstanceSingleton instance = null;

    private GameInstanceSingleton() { }

    public static GameInstanceSingleton getInstance() {
        if (instance == null) {
            instance = new GameInstanceSingleton();
        }
        return instance;
    }

    public void setMainCamera(OrthographicCamera camera) {
        this.mainCamera = camera;
    }

    public void setMainCharacter(MainCharacter character) {
        this.mainCharacter = character;
    }

    public OrthographicCamera getMainCamera() {
        if (mainCamera == null) {
            throw new IllegalStateException("main camera is not bounded !");
        }
        return mainCamera;
    }

    public MainCharacter getMainCharacter() {
        if (mainCharacter == null) {
            throw new IllegalStateException("main character is not bounded !");
        }
        return mainCharacter;
    }

}
