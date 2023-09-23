package controller.exception;

public class TakenUsername extends Exception {
    public TakenUsername() {
        super("This username is taken! Please choose another username!");
    }
}
