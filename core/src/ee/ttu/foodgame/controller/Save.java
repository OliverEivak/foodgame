package ee.ttu.foodgame.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import ee.ttu.foodgame.model.Scores;

public class Save {

    public static Scores s;

    public static void save() {
        for (int i : s.getScores()) {
            System.out.println(i);
        }
        FileHandle file = Gdx.files.local("highscores.dat");
        OutputStream out = null;

        try {
            file.writeBytes(serialize(s), false);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                }
            }
        }

    }

    public static void load() {
        if (!saveFileExists()) {
            init();
            return;
        }
        FileHandle file = Gdx.files.local("highscores.dat");

        try {
            s = (Scores) deserialize(file.readBytes());
        } catch (Exception e) {
            init();
        }
    }

    protected static byte[] serialize(Object o) throws IOException {
    	System.out.println("serialize");
    
    	
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bytes);
        out.writeObject(o);
        System.out.println(bytes.toByteArray());
        return bytes.toByteArray();
    }

    protected static Object deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(b);
        return in.readObject();
    }

    public static boolean saveFileExists() {
        FileHandle f = Gdx.files.local("highscores.dat");
        return f.exists();
    }

    public static void init() {
        s = new Scores();
        save();
    }
}
