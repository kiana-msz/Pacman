package controller.exception;

public class NotNumber extends Exception {
    public NotNumber() {
        super("It's not a number!Please enter a number!");
    }
}
