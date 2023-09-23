package view;

import controller.ScoreBoardController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreBoardView extends Application implements Initializable {
    private static Stage stage;
    private static ScoreBoardView instance = null;
    @FXML
    Label label = new Label();

    public static ScoreBoardView getInstance() {
        if (instance == null) instance = new ScoreBoardView();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardView.stage = stage;
        URL url = getClass().getResource("/ScoreBoardFinal.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(ScoreBoardController.getScores());
    }

    public void goBack() throws Exception {
        ScoreBoardController.goBack(stage);
    }
}
