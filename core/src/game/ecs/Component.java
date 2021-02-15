package game.ecs;

public abstract class Component {
    private String tag = null;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
