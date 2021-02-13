package game.system.inputs.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import game.system.inputs.InputWrapper;

public class DesktopInputs implements InputWrapper {

    @Override
    public boolean isForwardActivated() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    @Override
    public boolean isBackwardActivated() {
        return Gdx.input.isKeyPressed(Input.Keys.S);
    }

    @Override
    public boolean isLeftActivated() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    @Override
    public boolean isRightActivated() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

}
