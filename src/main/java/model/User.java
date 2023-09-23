package model;

import controller.JsonHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String name;
    private String password;
    private int maxScore;
    private LocalDateTime timeOfMaxScore;
    private int numberOfLives;
    private ArrayList<Maze> favoriteMazes = new ArrayList<>();
    private Maze selectedMaze;
    private Game previousGame;

    public static void deleteUser(User user) {
        User toDelete = null;
        for (User eachUser : allUsers) {
            if (eachUser.name.equals(user.name)) {
                toDelete = user;
                break;
            }
        }
        if (toDelete != null) {
            allUsers.remove(toDelete);
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.name.equals(username)) return user;
        }
        return null;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.maxScore = 0;
        this.numberOfLives = 3;
        this.timeOfMaxScore = LocalDateTime.now();
        allUsers.add(this);
        this.selectedMaze = new Maze(Maze.getMaze2());
        this.favoriteMazes.add(new Maze(Maze.getMaze2()));
        this.favoriteMazes.add(new Maze(Maze.getMaze1()));
        JsonHandler.getInstance().addUserToJson(this);
    }

    public void setPreviousGame(Game game) {
        this.previousGame = game;
    }

    public Game getPreviousGame() {
        return previousGame;
    }

    public ArrayList<Maze> getFavoriteMazes() {
        return this.favoriteMazes;
    }

    public void setSelectedMaze(Maze selectedMaze) {
        this.selectedMaze = selectedMaze;
    }

    public Maze getSelectedMaze() {
        return this.selectedMaze;
    }

    public int getNumberOfLives() {
        return this.numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public LocalDateTime getTimeOfMaxScore() {
        return this.timeOfMaxScore;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void addToFavoriteMazes(Maze maze) {
        this.favoriteMazes.add(maze);
    }

    public void upDateHighScore(int score) {
        if (!this.getName().equals("temp")) {
            if (this.maxScore < score) {
                this.maxScore = score;
                this.timeOfMaxScore = LocalDateTime.now();
            }
        }
    }

    @Override
    public String toString() {
        String toReturn = "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", maxScore=" + maxScore +
                ", timeOfMaxScore=" + timeOfMaxScore +
                ", numberOfLives=" + numberOfLives;
        for (Maze favoriteMaze : favoriteMazes) {
            toReturn += favoriteMaze.getMaze();
        }
        toReturn += ", selectedMaze=" + Arrays.deepToString(selectedMaze.getMaze()) +
                ", previousGame=" + previousGame +
                '}';
        return toReturn;
    }
}
