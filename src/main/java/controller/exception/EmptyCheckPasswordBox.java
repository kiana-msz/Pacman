package controller.exception;

public class EmptyCheckPasswordBox extends Exception {
    public EmptyCheckPasswordBox() {
        super("Check password box is empty!Please fill it!");
    }
}
