package com.github.grapixlegrand.desktopclient;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GdxGame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Host {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new GdxGame(), config);
    }
}

final class JavaProcess {

    private JavaProcess() {}

    public static int exec(Class klass, List<String> args) throws IOException,
            InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome +
                File.separator + "bin" +
                File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = klass.getName();

        List<String> command = new LinkedList<String>();
        command.add(javaBin);
        command.add("-cp");
        command.add(classpath);
        command.add(className);
        if (args != null) {
            command.addAll(args);
        }

        ProcessBuilder builder = new ProcessBuilder(command);

        Process process = builder.inheritIO().start();
        process.waitFor();
        return process.exitValue();
    }

}


public class MyClass {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new GdxGame(), config);

        try {
            int res = JavaProcess.exec(Host.class, new ArrayList<String>()); // Where the second window is shown
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}