package controller.exception;

public class WrongPassword extends Exception {
    public WrongPassword() {
        super("Wrong password! Try again!");
    }
}
