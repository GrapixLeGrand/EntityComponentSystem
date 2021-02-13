package game.system.inputs;

public interface InputProvider {

    static boolean isForwardActivated() {
        return InputManagerSingleton.getInstance().isForwardActivated();
    }

    static boolean isBackwardActivated() {
        return InputManagerSingleton.getInstance().isBackwardActivated();
    }

    static boolean isLeftActivated() {
        return InputManagerSingleton.getInstance().isLeftActivated();
    }

    static boolean isRightActivated() {
        return InputManagerSingleton.getInstance().isRightActivated();
    }
}
