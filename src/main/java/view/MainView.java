package view;

import controller.MainController;
import controller.MusicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class MainView extends Application {

    private static Stage stage;
    private static MainView instance = null;
    private static User user;

    public static MainView getInstance() {
        if (instance == null) instance = new MainView();
        return instance;
    }

    public void setCurrentUser(User user) {
        MainView.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView.stage = stage;
        URL url = getClass().getResource("/Main.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void logout() throws Exception {
        MainController.logout(stage, user);
    }

    public void deleteAccount() throws Exception {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.valueOf(yes), no, yes);
        alert.setHeaderText("You are deleting your account!");
        alert.setContentText("Are you sure?");
        alert.showAndWait();
        if (alert.getResult().equals(yes)) {
            MainController.deleteAccount(stage, user);
        }
    }

    public void changePassword() throws Exception {
        MainController.changePassword(stage, user);
    }

    public void showScoreBoard() throws Exception {
        MainController.showScoreBoard(stage);
    }

    public void startNewGame() throws Exception {
        MainController.startNewGame(stage, user);
    }

    public void muteUnmute() {
        MusicController.muteAndUnmuteForMainMusic();
    }
}
