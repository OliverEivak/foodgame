package ee.ttu.foodgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SFX {

    private static Sound button1;
    private static Sound button2;
    private static Sound rightAnswer;
    private static Sound wrongAnswer;
    private static Sound newHighest;
    private static boolean isLoaded = false;

    public static void initialize() {
        if (isLoaded == false) {
            System.out.println("Loading sound files");

            button1 = Gdx.audio.newSound(FileUtils.getFile("sounds/two_tone_nav.mp3"));
            button2 = Gdx.audio.newSound(FileUtils.getFile("sounds/notebook__key_press.mp3"));
            rightAnswer = Gdx.audio.newSound(FileUtils.getFile("sounds/correct_answer.mp3"));
            wrongAnswer = Gdx.audio.newSound(FileUtils.getFile("sounds/wrong_answer.mp3"));
            newHighest = Gdx.audio.newSound(FileUtils.getFile("sounds/applause_large.mp3"));

            isLoaded = true;
        }
    }

    public static void playButton1() {
        button1.play(1f);
    }

    public static void playButton2() {
        button2.play(1f);
    }

    public static void playRightAnswer() {
        rightAnswer.play(1f);
    }

    public static void playWrongAnswer() {
        wrongAnswer.play(1f);
    }

    public static void playApplause() {
        newHighest.play(1f);
    }

}
