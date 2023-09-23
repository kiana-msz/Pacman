package controller.exception;

public class DifferentPasswords extends Exception {
    public DifferentPasswords() {
        super("Given passwords are not equal! Try again!");
    }
}
