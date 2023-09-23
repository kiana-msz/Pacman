package view;

import controller.BeforeGameMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class BeforeGameMenuView extends Application {
    private static Stage stage;
    private static BeforeGameMenuView instance = null;
    private static User currentUser;

    public static BeforeGameMenuView getInstance() {
        if (instance == null) instance = new BeforeGameMenuView();
        return instance;
    }

    public void setCurrentUser(User user) {
        BeforeGameMenuView.currentUser = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BeforeGameMenuView.stage = stage;
        URL url = getClass().getResource("/BeforeGameMenu.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void showScoreBoard() throws Exception {
        BeforeGameMenuController.showScoreBoard(stage);
    }

    public void goBack() throws Exception {
        BeforeGameMenuController.goBack(stage, currentUser);
    }

    public void continuePreviousGame() throws Exception {
        BeforeGameMenuController.continuePreviousGame(stage, currentUser);
    }

    public void muteAndUnmute() {
        BeforeGameMenuController.muteUnmute();
    }

    public void chooseNumberOfLives() throws Exception {
        BeforeGameMenuController.chooseNumberOfLives(currentUser, stage);
    }

    public void chooseMaze() throws Exception {
        BeforeGameMenuController.chooseMaze(currentUser, stage);
    }

    public void startNewGame() throws Exception {
        BeforeGameMenuController.startNewGame(currentUser, stage);
    }
}
