package view;

import controller.ChooseNumberOfLivesController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseNumberOfLivesView extends Application implements Initializable {
    static ChooseNumberOfLivesView instance = null;
    static User currentUser;
    private static Stage stage;
    @FXML
    Label numberOfLives = new Label();

    public static ChooseNumberOfLivesView getInstance() {
        if (instance == null) instance = new ChooseNumberOfLivesView();
        return instance;
    }

    public void setCurrentUser(User currentUser) {
        ChooseNumberOfLivesView.currentUser = currentUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int numberOfLivesInt = currentUser.getNumberOfLives();
        numberOfLives.setText(String.valueOf(numberOfLivesInt));
    }

    public void start(Stage stage) throws Exception {
        ChooseNumberOfLivesView.stage = stage;
        URL url = getClass().getResource("/ChooseNumberOfLives.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void decrease() {
        int numberOfLivesInt = Integer.parseInt(numberOfLives.getText());
        if (numberOfLivesInt >= 3) {
            numberOfLivesInt--;
            numberOfLives.setText(String.valueOf(numberOfLivesInt));
        }
    }

    public void increase() {
        int numberOfLivesInt = Integer.parseInt(numberOfLives.getText());
        if (numberOfLivesInt <= 4) {
            numberOfLivesInt++;
            numberOfLives.setText(String.valueOf(numberOfLivesInt));
        }
    }

    public void save() throws Exception {
        ChooseNumberOfLivesController.save(currentUser, Integer.parseInt(numberOfLives.getText()), stage);
    }

    public void goBack() throws Exception {
        ChooseNumberOfLivesController.goBack(stage);
    }

}
