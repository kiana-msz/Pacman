package controller.exception;

public class EmptyNumber extends Exception {
    public EmptyNumber() {
        super("box is empty!Please fill it!");
    }
}
