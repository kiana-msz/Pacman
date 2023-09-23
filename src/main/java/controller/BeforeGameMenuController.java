package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Game;
import model.User;
import view.*;

public class BeforeGameMenuController {

    private static MenuTypes previousMenu;

    public static void showScoreBoard(Stage stage) throws Exception {
        ScoreBoardController.setPreviousMenu(MenuTypes.BEFOREGAMECONTROLLER);
        ScoreBoardView.getInstance().start(stage);
    }

    public static void goBack(Stage stage, User user) throws Exception {
        if (user.getName().equals("temp")) {
            User.deleteUser(user);
        }
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
        }
    }

    public static void startNewGame(User user, Stage stage) throws Exception {
        GameController.setPreviousMenu(MenuTypes.BEFOREGAMECONTROLLER);
        GameView.getInstance().setCurrentUser(user);
        Game game = new Game(user);
        GameView.getInstance().setGame(game);
        GameView.setMaze(user.getSelectedMaze());
        GameView.getInstance().start(stage);
    }

    public static void continuePreviousGame(Stage stage, User user) throws Exception {
        if (previousMenu.equals(MenuTypes.LOGINCONTROLLER) || (previousMenu.equals(MenuTypes.SIGNUPCONTROLLER))) {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.valueOf(yes), no, yes);
            alert.setHeaderText("you have to login before to continue previous game! otherwise you have to start a new game!");
            alert.setContentText("do you want to login?");
            alert.showAndWait();
            if (alert.getResult().equals(yes)) {
                LoginView.getInstance().start(stage);
            } else {
                Alert alertForNo = new Alert(Alert.AlertType.INFORMATION);
                alertForNo.setHeaderText("you chose not to login!");
                alertForNo.setContentText("you can't continue previous game!");
                alertForNo.showAndWait();
            }
        } else if (user.getPreviousGame() == null) {
            Alert alertForNo = new Alert(Alert.AlertType.INFORMATION);
            alertForNo.setHeaderText("you have no saved game!");
            alertForNo.setContentText("please start a new game!");
            alertForNo.showAndWait();
        }
    }

    public static void muteUnmute() {
        MusicController.muteAndUnmuteForMainMusic();
    }

    public static void chooseNumberOfLives(User currentUser, Stage stage) throws Exception {
        ChooseNumberOfLivesController.setPreviousMenu(MenuTypes.BEFOREGAMECONTROLLER);
        ChooseNumberOfLivesView.getInstance().setCurrentUser(currentUser);
        ChooseNumberOfLivesView.getInstance().start(stage);
    }

    public static void chooseMaze(User currentUser, Stage stage) throws Exception {
        FavoriteMazesController.setPreviousMenu(MenuTypes.BEFOREGAMECONTROLLER);
        FavoriteMazesView.getInstance().setCurrentUser(currentUser);
        FavoriteMazesView.getInstance().start(stage);
    }

    public static void setPreviousMenu(MenuTypes previousMenu) {
        BeforeGameMenuController.previousMenu = previousMenu;
    }
}
