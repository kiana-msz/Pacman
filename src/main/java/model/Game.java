package model;

import java.util.ArrayList;

public class Game {
    private int numberOfLivesLeft;
    private Maze maze;
    private int score;
    private ArrayList<XAndY> leftDots;

    public Game(User user) {
        this.numberOfLivesLeft = user.getNumberOfLives();
        this.maze = user.getSelectedMaze();
    }

    public int getScore() {
        return this.score;
    }

    public Maze getMaze() {
        return maze;
    }

    public int getNumberOfLivesLeft() {
        return numberOfLivesLeft;
    }

    public void setNumberOfLivesLeft(int numberOfLivesLeft) {
        this.numberOfLivesLeft = numberOfLivesLeft;
    }

    public ArrayList<XAndY> getLeftDots() {
        return leftDots;
    }

    public void setLeftDots(ArrayList<XAndY> leftDots) {
        this.leftDots = leftDots;
    }

    public void setScore(int i) {
        this.score = i;
    }
}
