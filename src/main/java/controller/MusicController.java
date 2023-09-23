package controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class MusicController {
    static MediaPlayer mediaPlayerForMain;
    static Music currentCondition;
    static MusicController instance = null;

    public static MusicController getInstance() {
        if (instance == null) instance = new MusicController();
        return instance;
    }

    public static void muteAndUnmuteForMainMusic() {
        if (currentCondition.equals(Music.PLAY)) {
            mediaPlayerForMain.pause();
            currentCondition = Music.PAUSE;
        } else if (currentCondition.equals(Music.PAUSE)) {
            mediaPlayerForMain.play();
            currentCondition = Music.PLAY;
        }
    }

    public void play() throws URISyntaxException {
        Media media = new Media(getClass().getResource("/pacman_beginning.wav").toURI().toString());
        mediaPlayerForMain = new MediaPlayer(media);
        mediaPlayerForMain.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayerForMain.play();
        currentCondition = Music.PLAY;
    }

    public void playWhenEatsDot() throws URISyntaxException {
        Media media = new Media(getClass().getResource("/pacman_eatfruit.wav").toURI().toString());
        MediaPlayer mediaPlayerForEatsDot = new MediaPlayer(media);
        mediaPlayerForEatsDot.setCycleCount(1);
        if (currentCondition.equals(Music.PLAY)) {
            mediaPlayerForEatsDot.play();
        }
    }

    public void playWhenFinishesDot() throws URISyntaxException {
        Media media = new Media(getClass().getResource("/pacman_chomp.wav").toURI().toString());
        MediaPlayer mediaPlayerForFinishesDots = new MediaPlayer(media);
        mediaPlayerForFinishesDots.setCycleCount(1);
        if (currentCondition.equals(Music.PLAY)) {
            mediaPlayerForFinishesDots.play();
        }
    }

    public void playWhenEatenByGhost() throws URISyntaxException {
        Media media = new Media(getClass().getResource("/pacman_death.wav").toURI().toString());
        MediaPlayer mediaPlayerForEatenByGhost = new MediaPlayer(media);
        mediaPlayerForEatenByGhost.setCycleCount(1);
        if (currentCondition.equals(Music.PLAY)) {
            mediaPlayerForEatenByGhost.play();
        }
    }

    public void playWhenGameOver() throws URISyntaxException {
        Media media = new Media(getClass().getResource("/pacmandeath2.mp3").toURI().toString());
        MediaPlayer mediaPlayerForGameOver = new MediaPlayer(media);
        mediaPlayerForGameOver.setCycleCount(1);
        if (currentCondition.equals(Music.PLAY)) {
            mediaPlayerForGameOver.play();
        }
    }
}
