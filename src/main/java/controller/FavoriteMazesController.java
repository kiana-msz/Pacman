package controller;

import javafx.stage.Stage;
import view.*;

public class FavoriteMazesController {

    private static MenuTypes previousMenu;

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
        FavoriteMazesController.previousMenu = previousMenu;
    }
}
