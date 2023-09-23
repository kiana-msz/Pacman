package view;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Game;
import model.Maze;
import model.User;

import java.util.Random;

public class GameView extends Application {
    public static User currentUser;
    private static Stage stage;
    private static Maze maze;
    public static Game game;
    private static GameController gameController;
    private static ImageView ghost1;
    private static ImageView ghost2;
    private static ImageView ghost3;
    private static ImageView ghost4;
    public static ImageView pacman;
    public static Image upPacman = new Image("/pacmanUp.gif");
    public static Image downPacman = new Image("/pacmanDown.gif");
    public static Image rightPacman = new Image("/pacmanRight.gif");
    public static Image leftPacman = new Image("/pacmanLeft.gif");
    static GameView instance = null;

    public static GameView getInstance() {
        if (instance == null) instance = new GameView();
        return instance;
    }

    static int takeRandomXAndY() {
        Random random = new Random();
        return ((random.nextInt(7)) + 10);
    }

    public static boolean pacmanCanGo(int y, int x) {
        return ((maze.getMaze()[y][x] == '0') || (maze.getMaze()[y][x] == '*'));
    }

    public static void setMaze(Maze maze) {
        GameView.maze = maze;
    }

    public static Maze getMaze() {
        return maze;
    }

    public static ImageView getGhost1() {
        return ghost1;
    }

    public static ImageView getGhost2() {
        return ghost2;
    }

    public static ImageView getGhost3() {
        return ghost3;
    }

    public static ImageView getGhost4() {
        return ghost4;
    }

    public static Stage getStage() {
        return stage;
    }

    public ImageView getPacman() {
        return pacman;
    }

    public void setGame(Game game) {
        GameView.game = game;
    }

    public void setCurrentUser(User currentUser) {
        GameView.currentUser = currentUser;
    }

    public void start(Stage stage) throws Exception {
        GameView.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/Game.fxml").openStream());
        gameController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.jpg"))));
        stage.setTitle("Pacman Game!");
        stage.show();
        gameController.startGhosts();
        pacman.requestFocus();
        gameController.handleKeyPressed();
    }

    public void setGhostsAndPacmanForView() {
        ghost1 = new ImageView("/ghost1.gif");
        ghost2 = new ImageView("/ghost2.gif");
        ghost3 = new ImageView("/ghost3.gif");
        ghost4 = new ImageView("/ghost4.gif");
        setPacman();
    }

    private void setPacman() {
        int x = takeRandomXAndY();
        int y = takeRandomXAndY();
        while (!((GameView.getMaze().getMaze()[y][x] == '*') || (GameView.getMaze().getMaze()[y][x] == '0'))) {
            x = takeRandomXAndY();
            y = takeRandomXAndY();
        }
        if ((GameView.getMaze().getMaze()[y][x + 1] == '*') || (GameView.getMaze().getMaze()[y][x + 1] == '0')) {
            pacman = new ImageView(rightPacman);
        } else if ((GameView.getMaze().getMaze()[y][x - 1] == '*') || (GameView.getMaze().getMaze()[y][x - 1] == '0')) {
            pacman = new ImageView(leftPacman);
        } else if ((GameView.getMaze().getMaze()[y + 1][x] == '*') || (GameView.getMaze().getMaze()[y + 1][x] == '0')) {
            pacman = new ImageView(downPacman);
        } else {
            pacman = new ImageView(upPacman);
        }
    }
}
