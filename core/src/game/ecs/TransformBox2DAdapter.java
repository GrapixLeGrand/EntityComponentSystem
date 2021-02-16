package game.ecs;

import game.ecs.components.Transform;

public class TransformBox2DAdapter {

    public static Transform fromBox2D(com.badlogic.gdx.physics.box2d.Transform t) {
        return new Transform(t.getPosition(), t.getRotation());
    }

    public static com.badlogic.gdx.physics.box2d.Transform toBox2D(Transform t) {
        com.badlogic.gdx.physics.box2d.Transform tb2 = new com.badlogic.gdx.physics.box2d.Transform();
        tb2.setPosition(t.getPosition());
        tb2.setRotation(t.getRotation());
        return tb2;
    }

    public static Transform updateFromBox2D(Transform t, com.badlogic.gdx.physics.box2d.Transform tb2) {
        t.setPosition(tb2.getPosition());
        t.setRotation(tb2.getRotation());
        return t;
    }

    public static com.badlogic.gdx.physics.box2d.Transform updateToBox2D(com.badlogic.gdx.physics.box2d.Transform tb2, Transform t) {
        tb2.setPosition(t.getPosition());
        tb2.setRotation(t.getRotation());
        return tb2;
    }
}
