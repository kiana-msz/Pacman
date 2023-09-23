package controller;

import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import view.GameView;

import java.net.URISyntaxException;
import java.util.Random;

public class GhostMover extends Transition {

    private ImageView ghost;

    public GhostMover(ImageView ghost) {
        this.ghost = ghost;
        this.setDelay(Duration.seconds(2));
        this.setCycleDuration(Duration.millis(200));
        this.setCycleCount(Timeline.INDEFINITE);
    }

    static int takeDirection() {
        Random random = new Random();
        return ((random.nextInt(4) + 1));
    }

    private int checkIfDirectionIsFine() {
        int direction = takeDirection();
        if (canGo(direction)) {
            return direction;
        }
        return -1;
    }


    private boolean canGo(int i) {
        if (i == 1) {
            if (GridPane.getColumnIndex(ghost) <= 23) //right
                return ((GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost)][GridPane.getColumnIndex(ghost) + 1] == '0') || (GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost)][GridPane.getColumnIndex(ghost) + 1] == '*'));
        }
        if (i == 2) {
            if (GridPane.getRowIndex(ghost) >= 1) //up
                return ((GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost) - 1][GridPane.getColumnIndex(ghost)] == '0') || (GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost) - 1][GridPane.getColumnIndex(ghost)] == '*'));
        }
        if (i == 3) {
            if (GridPane.getColumnIndex(ghost) >= 1) //left
                return ((GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost)][GridPane.getColumnIndex(ghost) - 1] == '0') || (GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost)][GridPane.getColumnIndex(ghost) - 1] == '*'));
        }
        if (i == 4) {
            if (GridPane.getRowIndex(ghost) <= 23) //down
                return ((GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost) + 1][GridPane.getColumnIndex(ghost)] == '0') || (GameView.game.getMaze().getMaze()[GridPane.getRowIndex(ghost) + 1][GridPane.getColumnIndex(ghost)] == '*'));
        }
        return false;
    }


    @Override
    protected void interpolate(double frac) {
        if (frac == 1) {
            int dx = 0, dy = 0;
            if (checkIfDirectionIsFine() == 1)
                dx = 1;
            else if (checkIfDirectionIsFine() == 2)
                dy = -1;
            else if (checkIfDirectionIsFine() == 3)
                dx = -1;
            else if (checkIfDirectionIsFine() == 4)
                dy = 1;
            GameController.moveGhost(ghost, dx, dy);
        }
    }

}



