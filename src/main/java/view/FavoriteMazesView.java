package view;

import controller.ChooseMazeController;
import controller.FavoriteMazesController;
import controller.MenuTypes;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Maze;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteMazesView extends Application implements Initializable {

    static FavoriteMazesView instance = null;
    static User currentUser;
    private static Stage stage;
    private static Maze maze;
    @FXML
    GridPane gridPaneForMaze;

    public static FavoriteMazesView getInstance() {
        if (instance == null) instance = new FavoriteMazesView();
        return instance;
    }

    public void setCurrentUser(User currentUser) {
        FavoriteMazesView.currentUser = currentUser;
    }

    public void start(Stage stage) throws Exception {
        FavoriteMazesView.stage = stage;
        URL url = getClass().getResource("/FavoriteMazes.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
    }


    public void muteAndUnmute(MouseEvent mouseEvent) {
        ChooseMazeController.muteUnmute();
    }

    public void goBack() throws Exception {
        FavoriteMazesController.goBack(stage);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Maze> favoriteMazes = currentUser.getFavoriteMazes();
        if (favoriteMazes.isEmpty()) {
            showPopUpForEmptyFavoriteMaze();
        } else {
            maze = favoriteMazes.get(0);
            ChooseMazeController.fillGridPane(gridPaneForMaze, maze);
        }
    }


    public void next() {
        ArrayList<Maze> favoriteMazes = currentUser.getFavoriteMazes();
        if ((maze != null) && (favoriteMazes.size() != 0)) {
            int index = favoriteMazes.indexOf(maze);
            if (index < favoriteMazes.size() - 1) {
                maze = favoriteMazes.get(index + 1);
                ChooseMazeController.fillGridPane(gridPaneForMaze, maze);
            } else {
                maze = favoriteMazes.get(0);
                ChooseMazeController.fillGridPane(gridPaneForMaze, maze);
            }
        }
    }

    public static void showPopUpForEmptyFavoriteMaze() {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Nothing to show");
        error.setContentText("you can add a maze to your favorite list by using new map button.");
        error.showAndWait();
    }

    public void createNewMaze() throws Exception {
        ChooseMazeController.setPreviousMenu(MenuTypes.FAVORITEMAZECONTROLLER);
        ChooseMazeView.getInstance().setCurrentUser(currentUser);
        ChooseMazeView.getInstance().start(stage);
    }

    public void select() {
        currentUser.setSelectedMaze(maze);
    }

}
