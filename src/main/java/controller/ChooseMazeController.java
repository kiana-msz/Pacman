package controller;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Maze;
import model.User;
import view.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChooseMazeController {

    private static MenuTypes previousMenu;

    public static void save(User user, Maze maze) {
        user.addToFavoriteMazes(maze);
        ChooseMazeView.showAddedSuccessfully();
    }

    public static void muteUnmute() {
        MusicController.muteAndUnmuteForMainMusic();
    }

    public static void goBack(Stage stage) throws Exception {
        if (previousMenu.equals(MenuTypes.BEFOREGAMECONTROLLER)) {
            BeforeGameMenuView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.LOGINCONTROLLER)) {
            LoginView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.SIGNUPCONTROLLER)) {
            SignUpView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.MAINCONTROLLER)) {
            MainView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.CHANGEPASSWORDCONTROLLER)) {
            ChangePasswordView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.SCOREBOARDCONTROLLER)) {
            ScoreBoardView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.FAVORITEMAZECONTROLLER)) {
            FavoriteMazesView.getInstance().start(stage);
        }
    }

    public static void fillGridPane(GridPane gridPane, Maze maze) {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Rectangle wall = new Rectangle(20, 20);
                if (maze.getMaze()[j][i] == '1') {
                    wall.setFill(Color.DARKCYAN);
                }
                gridPane.add(wall, j, i);
            }
        }
    }

    public static void setPreviousMenu(MenuTypes previousMenu) {
        ChooseMazeController.previousMenu = previousMenu;
    }
}
