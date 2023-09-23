package controller;

import javafx.stage.Stage;
import model.User;
import view.*;

import java.util.Comparator;
import java.util.List;

public class ScoreBoardController {

    private static MenuTypes previousMenu;

    public static void setPreviousMenu(MenuTypes previousMenu) {
        ScoreBoardController.previousMenu = previousMenu;
    }

    public static String getScores() {
        List<User> allUsers = User.getAllUsers();
        Comparator<User> ComparatorForAllUsers = Comparator.comparing(User::getMaxScore, Comparator.reverseOrder()).thenComparing(User::getTimeOfMaxScore);
        allUsers.sort(ComparatorForAllUsers);
        User previousUser = null;
        int rank = 1;
        StringBuilder toReturn = new StringBuilder();
        int i = 1;
        int userCounter = 0;
        User toBeDeleted = null;
        for (User eachUser : allUsers) {
            if (eachUser.getName().equals("temp")) {
                toBeDeleted = eachUser;
            }
        }
        if (toBeDeleted != null) {
            allUsers.remove(toBeDeleted);
        }
        for (User eachUser : allUsers) {
            if (userCounter >= 10) {
                return toReturn.toString();
            } else {
                if (previousUser != null) {
                    if (previousUser.getMaxScore() == eachUser.getMaxScore()) {
                        i++;
                    } else {
                        rank += i;
                        i = 1;
                    }
                }
                toReturn.append(rank).append("           Name: ").append(eachUser.getName()).append("           Score: ").append(eachUser.getMaxScore()).append("\n");
                previousUser = eachUser;
                userCounter++;
            }
        }
        return toReturn.toString();
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
}
