package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Maze;
import model.XAndY;
import view.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import static view.GameView.*;

public class GameController implements Initializable {

    static int y;
    static int x;
    private static GameController instance = null;
    public static String playOrPause;
    private static MenuTypes previousMenu;
    @FXML
    GridPane gridPaneForMaze;
    @FXML
    private Label lives;
    @FXML
    private Label score;
    private GhostMover ghost1Move;
    private GhostMover ghost2Move;
    private GhostMover ghost3Move;
    private GhostMover ghost4Move;

    static int takeRandomXAndY() {
        Random random = new Random();
        return ((random.nextInt(7)) + 10);
    }

    public static void goBack(Stage stage) throws Exception {
        if (previousMenu.equals(MenuTypes.BEFOREGAMECONTROLLER)) {
            BeforeGameMenuView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.LOGINCONTROLLER)) {
            LoginView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.SIGNUPCONTROLLER)) {
            SignUpView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.MAINCONTROLLER)) {
            MainView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.CHANGEPASSWORDCONTROLLER)) {
            ChangePasswordView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.SCOREBOARDCONTROLLER)) {
            ScoreBoardView.getInstance().start(stage);
        } else if (previousMenu.equals(MenuTypes.FAVORITEMAZECONTROLLER)) {
            FavoriteMazesView.getInstance().start(stage);
        }
    }

    public static void setPreviousMenu(MenuTypes previousMenu) {
        GameController.previousMenu = previousMenu;
    }

    public static void moveGhost(ImageView imageView, int dx, int dy) {
        GridPane.setColumnIndex(imageView, GridPane.getColumnIndex(imageView) + dx);
        GridPane.setRowIndex(imageView, GridPane.getRowIndex(imageView) + dy);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playOrPause = "play";
        fillGridPane(GameView.game.getMaze());
        lives.setText(String.valueOf(GameView.game.getNumberOfLivesLeft()));
        score.setText(String.valueOf(GameView.game.getScore()));
        GameView.getInstance().setGhostsAndPacmanForView();
        x = takeRandomXAndY();
        y = takeRandomXAndY();
        while (GameView.getMaze().getMaze()[y][x] == '1') {
            x = takeRandomXAndY();
            y = takeRandomXAndY();
        }
        setGhostsAndPacmanForController(pacman, x, y);
        setGhostsAndPacmanForController(GameView.getGhost1(), 1, 1);
        setGhostsAndPacmanForController(GameView.getGhost2(), 1, 23);
        setGhostsAndPacmanForController(GameView.getGhost3(), 23, 1);
        setGhostsAndPacmanForController(GameView.getGhost4(), 23, 23);
    }

    public static GameController getInstance() {
        if (instance == null) instance = new GameController();
        return instance;
    }

    private void fillGridPane(Maze maze) {
        ArrayList<XAndY> dots = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Rectangle rectangle = new Rectangle(20, 20);
                if (maze.getMaze()[j][i] == '1') {
                    rectangle.setFill(Color.DARKCYAN);
                }
                gridPaneForMaze.add(rectangle, i, j);
                if ((maze.getMaze()[j][i] == '*') || (maze.getMaze()[j][i] == '0')) {
                    dots.add(new XAndY(i, j));
                    Circle circle = new Circle(3);
                    GridPane.setHalignment(circle, HPos.CENTER);
                    circle.setFill(Color.HOTPINK);
                    gridPaneForMaze.add(circle, i, j);
                    circle.toFront();
                }
            }
        }
        game.setLeftDots(dots);
    }

    public void setGhostsAndPacmanForController(ImageView image, int i, int j) {
        image.setFitHeight(20);
        image.setFitWidth(20);
        gridPaneForMaze.add(image, i, j);
    }

    public void muteAndUnmute() {
        MusicController.muteAndUnmuteForMainMusic();
    }

    public void goBack() throws Exception {
        GameView.currentUser.setPreviousGame(game);
        goBack(GameView.getStage());
    }

    public void handleKeyPressed() {
        Stage stage = GameView.getStage();
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            playOrPause = "play";
            String keyName = event.getCode().getName();
            if (keyName.equals("Right")) {
                pacman.setImage(GameView.rightPacman);
                if (GameView.pacmanCanGo(GridPane.getRowIndex(pacman), GridPane.getColumnIndex(pacman) + 1)) {
                    GridPane.setColumnIndex(GameView.pacman, GridPane.getColumnIndex(GameView.pacman) + 1);
                    try {
                        eatDot(GridPane.getColumnIndex(pacman), GridPane.getRowIndex(pacman));
                        handlePacmanAndGhostCollision();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            } else if (keyName.equals("Up")) {
                pacman.setImage(GameView.upPacman);
                if (GameView.pacmanCanGo(GridPane.getRowIndex(pacman) - 1, GridPane.getColumnIndex(pacman))) {
                    GridPane.setRowIndex(GameView.pacman, GridPane.getRowIndex(GameView.pacman) - 1);
                    try {
                        eatDot(GridPane.getColumnIndex(pacman), GridPane.getRowIndex(pacman));
                        handlePacmanAndGhostCollision();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            } else if (keyName.equals("Left")) {
                pacman.setImage(GameView.leftPacman);
                if (GameView.pacmanCanGo(GridPane.getRowIndex(pacman), GridPane.getColumnIndex(pacman) - 1)) {
                    GridPane.setColumnIndex(GameView.pacman, GridPane.getColumnIndex(GameView.pacman) - 1);
                    try {
                        eatDot(GridPane.getColumnIndex(pacman), GridPane.getRowIndex(pacman));
                        handlePacmanAndGhostCollision();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            } else if (keyName.equals("Down")) {
                pacman.setImage(GameView.downPacman);
                if (GameView.pacmanCanGo(GridPane.getRowIndex(pacman) + 1, GridPane.getColumnIndex(pacman))) {
                    GridPane.setRowIndex(GameView.pacman, GridPane.getRowIndex(GameView.pacman) + 1);
                    try {
                        eatDot(GridPane.getColumnIndex(pacman), GridPane.getRowIndex(pacman));
                        handlePacmanAndGhostCollision();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void eatDot(int column, int row) throws URISyntaxException {
        int numberOfNodesInThisBlock = 0;
        XAndY xAndY;
        for (Node node : gridPaneForMaze.getChildren()) {
            if ((node instanceof Rectangle || node instanceof Circle) && GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                numberOfNodesInThisBlock++;
            }
        }
        if (numberOfNodesInThisBlock % 2 == 0) {
            xAndY = new XAndY(column, row);
            ArrayList<XAndY> leftDots = game.getLeftDots();
            if (leftDots.contains(xAndY)) {
                leftDots.remove(xAndY);
                game.setLeftDots(leftDots);
            }
            Rectangle rectangle = new Rectangle(20, 20);
            game.setScore(game.getScore() + 5);
            score.setText(String.valueOf(game.getScore()));
            gridPaneForMaze.add(rectangle, column, row);
            rectangle.toFront();
            MusicController.getInstance().playWhenEatsDot();
            if (dotsAreFinished()) {
                startOver();
            }
            pacmanAndGhostsToFront();
        }
    }

    public void handlePacmanAndGhostCollision() throws URISyntaxException {
        if (hasCollision()) {
            game.setNumberOfLivesLeft(game.getNumberOfLivesLeft() - 1);
            lives.setText(String.valueOf(game.getNumberOfLivesLeft()));
            if (game.getNumberOfLivesLeft() == 0) {
                endGame();
            } else {
                MusicController.getInstance().playWhenEatenByGhost();
                GridPane.setColumnIndex(pacman, x);
                GridPane.setRowIndex(pacman, y);
                playOrPause = "pause";
            }
        }
    }

    private void endGame() throws URISyntaxException {
        currentUser.upDateHighScore(game.getScore());
        stop();
        MusicController.getInstance().playWhenGameOver();
        ButtonType startNewGame = new ButtonType("New Game");
        ButtonType goBack = new ButtonType("Back");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You lost the game!", startNewGame, goBack);
        alert.show();
        alert.setOnCloseRequest(event -> {
            ButtonType result = alert.getResult();
            if (result.equals(startNewGame)) {
                try {
                    GameView.getInstance().start(GameView.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (result.equals(goBack)) {
                try {
                    goBack(GameView.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stop() {
        ghost1Move.pause();
        ghost2Move.pause();
        ghost3Move.pause();
        ghost4Move.pause();
    }

    private boolean hasCollision() {
        return ((GridPane.getColumnIndex(getGhost1()).equals(GridPane.getColumnIndex(pacman)) && GridPane.getRowIndex(getGhost1()).equals(GridPane.getRowIndex(pacman))) ||
                (GridPane.getColumnIndex(getGhost2()).equals(GridPane.getColumnIndex(pacman)) && GridPane.getRowIndex(getGhost2()).equals(GridPane.getRowIndex(pacman))) ||
                (GridPane.getColumnIndex(getGhost3()).equals(GridPane.getColumnIndex(pacman)) && GridPane.getRowIndex(getGhost3()).equals(GridPane.getRowIndex(pacman))) ||
                (GridPane.getColumnIndex(getGhost4()).equals(GridPane.getColumnIndex(pacman)) && GridPane.getRowIndex(getGhost4()).equals(GridPane.getRowIndex(pacman))));
    }

    private void startOver() throws URISyntaxException {
        fillGridPane(GameView.game.getMaze());
        game.setNumberOfLivesLeft(game.getNumberOfLivesLeft() + 1);
        lives.setText(String.valueOf(game.getNumberOfLivesLeft()));
        MusicController.getInstance().playWhenFinishesDot();
        playOrPause = "pause";
    }

    private boolean dotsAreFinished() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if ((game.getMaze().getMaze()[i][j] == '0') || (game.getMaze().getMaze()[i][j] == '*')) {
                    int numberOfNodesInThisBlock = 0;
                    for (Node node : gridPaneForMaze.getChildren()) {
                        if ((node instanceof Rectangle || node instanceof Circle) && (GridPane.getColumnIndex(node) == i) && (GridPane.getRowIndex(node) == j)) {
                            numberOfNodesInThisBlock++;
                        }
                    }
                    if (numberOfNodesInThisBlock % 2 == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void pacmanAndGhostsToFront() {
        pacman.toFront();
        GameView.getGhost1().toFront();
        GameView.getGhost2().toFront();
        GameView.getGhost3().toFront();
        GameView.getGhost4().toFront();
    }

    public void startGhosts() {
        ghost1Move = new GhostMover(GameView.getGhost1());
        ghost1Move.play();
        ghost2Move = new GhostMover(GameView.getGhost2());
        ghost2Move.play();
        ghost3Move = new GhostMover(GameView.getGhost3());
        ghost3Move.play();
        ghost4Move = new GhostMover(GameView.getGhost4());
        ghost4Move.play();
    }


    public void pauseOrPlay() {
        if (playOrPause.equals("pause")) {
            playOrPause = "play";
        } else if (playOrPause.equals("play")) {
            playOrPause = "pause";
        }
    }
}
