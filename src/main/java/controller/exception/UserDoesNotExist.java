package controller.exception;

public class UserDoesNotExist extends Exception {
    public UserDoesNotExist() {
        super("There is no user with this username");
    }
}
