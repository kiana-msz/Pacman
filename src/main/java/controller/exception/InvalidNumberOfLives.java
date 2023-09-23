package controller.exception;

public class InvalidNumberOfLives extends Exception {
    public InvalidNumberOfLives() {
        super("your number has to be between 2 and 5");
    }
}
