package view;

import controller.ChooseMazeController;
import controller.MazeGenerator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Maze;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseMazeView extends Application implements Initializable {

    static ChooseMazeView instance = null;
    static User currentUser;
    private static Stage stage;
    private static Maze maze;
    @FXML
    GridPane gridPaneForMaze;

    public static void showAddedSuccessfully() {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Done");
        error.setContentText("This maze was added to your favorite mazes successfully!");
        error.showAndWait();
    }

    public static ChooseMazeView getInstance() {
        if (instance == null) instance = new ChooseMazeView();
        return instance;
    }

    public void setCurrentUser(User currentUser) {
        ChooseMazeView.currentUser = currentUser;
    }

    public void start(Stage stage) throws Exception {
        ChooseMazeView.stage = stage;
        URL url = getClass().getResource("/ChooseMaze.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }

    public void muteAndUnmute() {
        ChooseMazeController.muteUnmute();
    }

    public void goBack() throws Exception {
        ChooseMazeController.goBack(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createNewMaze();
    }

    public void saveAsFavorite() {
        ChooseMazeController.save(currentUser, maze);
    }

    public void next() {
        createNewMaze();
    }

    private void createNewMaze() {
        maze = new Maze(MazeGenerator.generateMaze());
        ChooseMazeController.fillGridPane(gridPaneForMaze, maze);

    }

    public void select() {
        currentUser.setSelectedMaze(maze);
    }

}
