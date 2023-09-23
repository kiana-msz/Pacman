package view;

import controller.MusicController;
import controller.SignUpController;
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

public class SignUpView extends Application {
    private static Stage stage;
    static SignUpView instance = null;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField checkPassword;

    public static SignUpView getInstance() {
        if (instance == null) instance = new SignUpView();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (LoginView.instance == null && (SignUpView.instance == null)) {
            MusicController.getInstance().play();
        }
        SignUpView.stage = stage;
        URL url = getClass().getResource("/SignUp.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void login() throws Exception {
        SignUpController.login(stage);
    }

    public void createAccount() {
        try {
            SignUpController.createAccount(username.getText(), password.getText(), checkPassword.getText(), stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    public void showScoreBoard() throws Exception {
        SignUpController.showScoreBoard(stage);
    }

    public void startNewGame() throws Exception {
        SignUpController.startNewGame(stage);
    }

    public void muteUnmute() {
        SignUpController.muteUnmute();
    }

}
