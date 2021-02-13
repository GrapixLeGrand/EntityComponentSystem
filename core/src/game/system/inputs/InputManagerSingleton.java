package game.system.inputs;

import game.actors.Constants;
import game.system.inputs.platform.DesktopInputs;

public class InputManagerSingleton {

    private static InputWrapper instance = null;

    protected static InputWrapper getInstance() {
        if (instance == null) {
            if (Constants.CURRENT_PLATFORM == Constants.PLATFORM.DESKTOP) {
                instance = new DesktopInputs();
            } else {
                throw new IllegalStateException("Inputs for current platform are not supported");
            }
        }
        return instance;
    }

}
