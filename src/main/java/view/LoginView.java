package view;

import controller.LoginController;
import controller.MusicController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class LoginView extends Application {
    private static Stage stage;
    static LoginView instance = null;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public static LoginView getInstance() {
        if (instance == null) instance = new LoginView();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        if ((LoginView.instance == null) && (SignUpView.instance == null)) {
            MusicController.getInstance().play();
        }
        LoginView.stage = stage;
        URL url = getClass().getResource("/Login.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void signup() throws Exception {
        LoginController.signup(stage);
    }

    public void login() {
        try {
            LoginController.login(username.getText(), password.getText(), stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    public void showScoreBoard() throws Exception {
        LoginController.showScoreBoard(stage);
    }

    public void startNewGame() throws Exception {
        LoginController.startNewGame(stage);
    }

    public void muteUnmute() {
        LoginController.muteUnmute();
    }
}
