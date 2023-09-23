package controller;

import controller.exception.*;
import javafx.stage.Stage;
import model.User;
import view.ChangePasswordView;
import view.MainView;

public class ChangePasswordController {

    public static void goBack(Stage stage) throws Exception {
        MainView.getInstance().start(stage);
    }

    public static void changePassword(User user, String password, String checkPassword, Stage stage) throws Exception {
        if (password.equals("")) throw new EmptyPasswordBox();
        if (checkPassword.equals("")) throw new EmptyCheckPasswordBox();
        if (!passwordsAreEqual(password, checkPassword)) throw new DifferentPasswords();
        user.setPassword(password);
        ChangePasswordView.showPasswordChanged(user);
        MainView.getInstance().start(stage);
    }

    public static boolean passwordsAreEqual(String firstPassword, String secondPassword) {
        return firstPassword.equals(secondPassword);
    }
}
