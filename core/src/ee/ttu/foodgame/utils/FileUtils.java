package ee.ttu.foodgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileUtils {

    /**
     * A method for retrieving files that also works in tests.
     */
    public static FileHandle getFile(String path) {
        FileHandle file = null;
        if (Gdx.files.internal(path).exists()) {
            file = Gdx.files.internal(path);
        } else if (Gdx.files.internal("../android/assets/" + path).exists()) {
            file = Gdx.files.internal("../android/assets/" + path);
        }
        return file;
    }

}
