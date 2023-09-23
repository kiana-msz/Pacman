package view;

import controller.ChangePasswordController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChangePasswordView extends Application {

    private static Stage stage;
    private static ChangePasswordView instance = null;
    private static User user;
    @FXML
    private TextField password;
    @FXML
    private TextField checkPassword;

    public static void showPasswordChanged(User user) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Done");
        error.setContentText("Dear " + user.getName() + " your password was changed successfully!");
        error.showAndWait();
    }

    public static ChangePasswordView getInstance() {
        if (instance == null) instance = new ChangePasswordView();
        return instance;
    }

    public void setCurrentUser(User user) {
        ChangePasswordView.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ChangePasswordView.stage = stage;
        URL url = getClass().getResource("/ChangePassword.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void goBack() throws Exception {
        ChangePasswordController.goBack(stage);
    }

    public void changePassword() {
        try {
            ChangePasswordController.changePassword(user, password.getText(), checkPassword.getText(), stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
}
