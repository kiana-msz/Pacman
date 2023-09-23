package controller;

import javafx.stage.Stage;
import model.User;
import view.*;

public class MainController {
    public static void logout(Stage stage, User user) throws Exception {
        if (user.getName().equals("temp")) {
            User.deleteUser(user);
        }
        LoginView.getInstance().start(stage);
    }

    public static void deleteAccount(Stage stage, User user) throws Exception {
        User.deleteUser(user);
        SignUpView.getInstance().start(stage);
    }

    public static void changePassword(Stage stage, User user) throws Exception {
        ChangePasswordView.getInstance().setCurrentUser(user);
        ChangePasswordView.getInstance().start(stage);
    }

    public static void showScoreBoard(Stage stage) throws Exception {
        ScoreBoardController.setPreviousMenu(MenuTypes.MAINCONTROLLER);
        ScoreBoardView.getInstance().start(stage);
    }

    public static void startNewGame(Stage stage, User user) throws Exception {
        BeforeGameMenuController.setPreviousMenu(MenuTypes.MAINCONTROLLER);
        BeforeGameMenuView.getInstance().setCurrentUser(user);
        BeforeGameMenuView.getInstance().start(stage);
    }
}
