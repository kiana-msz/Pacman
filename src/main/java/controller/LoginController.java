package controller;

import controller.exception.*;
import javafx.stage.Stage;
import model.User;
import view.*;


public class LoginController {

    public static void login(String username, String password, Stage stage) throws Exception {
        if (username.equals("")) {
            throw new EmptyUsernameBox();
        }
        if (password.equals("")) {
            throw new EmptyPasswordBox();
        }
        User user = User.getUserByUsername(username);
        if (User.getUserByUsername(username) == null) {
            throw new UserDoesNotExist();
        }
        if (!user.getPassword().equals(password)) {
            throw new WrongPassword();
        }
        MainView.getInstance().setCurrentUser(user);
        MainView.getInstance().start(stage);
    }

    public static void signup(Stage stage) throws Exception {
        SignUpView.getInstance().start(stage);
    }

    public static void showScoreBoard(Stage stage) throws Exception {
        ScoreBoardController.setPreviousMenu(MenuTypes.LOGINCONTROLLER);
        ScoreBoardView.getInstance().start(stage);
    }

    public static void startNewGame(Stage stage) throws Exception {
        BeforeGameMenuController.setPreviousMenu(MenuTypes.LOGINCONTROLLER);
        User temp = new User("temp", "temp");
        BeforeGameMenuView.getInstance().setCurrentUser(temp);
        BeforeGameMenuView.getInstance().start(stage);
    }

    public static void muteUnmute() {
        MusicController.muteAndUnmuteForMainMusic();
    }

}
