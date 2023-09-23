package controller;

import controller.exception.*;
import javafx.stage.Stage;
import view.BeforeGameMenuView;
import view.LoginView;
import model.*;
import view.ScoreBoardView;

public class SignUpController {

    public static void createAccount(String username, String password, String checkPassword, Stage stage) throws Exception {
        if (username.equals("")) throw new EmptyUsernameBox();
        if (password.equals("")) throw new EmptyPasswordBox();
        if (checkPassword.equals("")) throw new EmptyCheckPasswordBox();
        User user = User.getUserByUsername(username);
        if (!passwordsAreEqual(password, checkPassword)) throw new DifferentPasswords();
        if (user != null) throw new TakenUsername();
        new User(username, password);
        LoginView.getInstance().start(stage);
    }

    public static boolean passwordsAreEqual(String firstPassword, String secondPassword) {
        return firstPassword.equals(secondPassword);
    }

    public static void login(Stage stage) throws Exception {
        LoginView.getInstance().start(stage);
    }

    public static void showScoreBoard(Stage stage) throws Exception {
        ScoreBoardController.setPreviousMenu(MenuTypes.SIGNUPCONTROLLER);
        ScoreBoardView.getInstance().start(stage);
    }

    public static void startNewGame(Stage stage) throws Exception {
        BeforeGameMenuController.setPreviousMenu(MenuTypes.SIGNUPCONTROLLER);
        User temp = new User("temp", "temp");
        BeforeGameMenuView.getInstance().setCurrentUser(temp);
        BeforeGameMenuView.getInstance().start(stage);
    }

    public static void muteUnmute() {
        MusicController.muteAndUnmuteForMainMusic();
    }

}
