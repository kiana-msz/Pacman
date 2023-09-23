package controller;

import javafx.stage.Stage;
import model.User;
import view.*;

public class ChooseNumberOfLivesController {

    private static MenuTypes previousMenu;

    public static void save(User currentUser, int numberOfLives, Stage stage) throws Exception {
        currentUser.setNumberOfLives(numberOfLives);
        BeforeGameMenuView.getInstance().start(stage);
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
        }
    }

    public static void setPreviousMenu(MenuTypes previousMenu) {
        ChooseNumberOfLivesController.previousMenu = previousMenu;
    }
}
