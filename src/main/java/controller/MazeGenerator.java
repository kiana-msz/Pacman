package controller;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {

    public static void main(String[] args) {
        char[][] board = generateMaze();
        for (int j = 0; j < 25; j++) {
            System.out.println(board[j]);
        }
        System.out.println();
    }

    public static char[][] generateMaze() {
        char[][] board = new char[25][25];
        makeBoardEmpty(board);
        createMaze(board);
        deleteSome1s(board);
        return board;
    }

    static void makeBoardEmpty(char[][] board) {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (((j % 2) == 1) && ((i % 2) == 1)) {
                    board[i][j] = '_';
                } else {
                    board[i][j] = '1';
                }
            }
        }
        board[0][1] = '1';
        board[25 - 1][25 - 2] = '1';
        board[1][1] = '*';
    }

    private static void createMaze(char[][] board) {
        ArrayList<Integer> doneXs = new ArrayList<>();
        ArrayList<Integer> doneYs = new ArrayList<>();
        int currentX = 1;
        int currentY = 1;
        doneXs.add(1);
        doneYs.add(1);
        while (true) {
            if ((currentX == 1) && (currentY == 1) && (board[3][1] == '*') && (board[1][3] == '*')) {
                break;
            } else if (thisOneIsDone(board, currentX, currentY)) {
                int size = doneXs.size();
                currentX = doneXs.get(size - 2);
                currentY = doneYs.get(size - 2);
                doneXs.remove(size - 1);
                doneYs.remove(size - 1);
            } else {
                int direction = takeDirection();
                switch (direction) {
                    case 1: //right
                        if (isFine(direction, currentX, currentY, board)) {
                            moveRight(currentX, currentY, board, doneXs, doneYs);
                            currentX += 2;
                        }
                        break;
                    case 2: //left
                        if (isFine(direction, currentX, currentY, board)) {
                            moveLeft(currentX, currentY, board, doneXs, doneYs);
                            currentX -= 2;
                        }
                        break;
                    case 3: //down
                        if (isFine(direction, currentX, currentY, board)) {
                            moveDown(currentX, currentY, board, doneXs, doneYs);
                            currentY += 2;
                        }
                        break;
                    case 4: //up
                        if (isFine(direction, currentX, currentY, board)) {
                            moveUp(currentX, currentY, board, doneXs, doneYs);
                            currentY -= 2;
                        }
                        break;
                }
            }
        }
    }

    static void deleteSome1s(char[][] maze) {
        int numberOfDeletes = 0;
        while (numberOfDeletes < 40) {
            int x = takeRandomXAndY();
            int y = takeRandomXAndY();
            if (maze[y][x] == '1') {
                if (!(((maze[y - 1][x] == '1')) && ((maze[y + 1][x] == '1')) && ((maze[y][x - 1] == '1')) && ((maze[y][x + 1] == '1')))) {
                    maze[y][x] = '0';
                    numberOfDeletes++;
                }
            }
        }
    }


    static int takeRandomXAndY() {
        Random random = new Random();
        return ((random.nextInt(23)) + 1);
    }

    static int takeDirection() {
        Random random = new Random();
        return ((random.nextInt(4) + 1));
    }

    static boolean isFine(int direction, int currentX, int currentY, char[][] board) {
        switch (direction) {
            case 1: //right
                return ((currentX + 2) < (25 - 1)) && (board[currentY][currentX + 1] == '1') && (board[currentY][currentX + 2] == '_');
            case 2: //left
                return ((currentX - 2) > 0) && (board[currentY][currentX - 1] == '1') && (board[currentY][currentX - 2] == '_');
            case 3: //down
                return ((currentY + 2) < (25 - 1)) && (board[currentY + 1][currentX] == '1') && (board[currentY + 2][currentX] == '_');
            case 4: //up
                return ((currentY - 2) > 0) && (board[currentY - 1][currentX] == '1') && (board[currentY - 2][currentX] == '_');
        }
        return false;
    }

    static boolean thisOneIsDone(char[][] board, int currentX, int currentY) {
        int numberOfBlocks = 1;
        int numberOfCheckedBlocks = 1;
        if ((currentY - 2) > 0) {
            numberOfBlocks++;
            if (board[currentY - 2][currentX] == '*') numberOfCheckedBlocks++;
        }
        if ((currentX - 2) > 0) {
            numberOfBlocks++;
            if (board[currentY][currentX - 2] == '*') numberOfCheckedBlocks++;
        }
        if ((currentX + 2) < (25 - 1)) {
            numberOfBlocks++;
            if (board[currentY][currentX + 2] == '*') numberOfCheckedBlocks++;
        }
        if ((currentY + 2) < (25 - 1)) {
            numberOfBlocks++;
            if (board[currentY + 2][currentX] == '*') numberOfCheckedBlocks++;
        }
        return numberOfBlocks == numberOfCheckedBlocks;
    }

    static void moveRight(int currentX, int currentY, char[][] board, ArrayList<Integer> doneXs, ArrayList<Integer> doneYs) {
        board[currentY][currentX + 1] = '0';
        board[currentY][currentX + 2] = '*';
        currentX += 2;
        doneXs.add(currentX - 2);
        doneYs.add(currentY);
    }

    static void moveLeft(int currentX, int currentY, char[][] board, ArrayList<Integer> doneXs, ArrayList<Integer> doneYs) {
        board[currentY][currentX - 1] = '0';
        board[currentY][currentX - 2] = '*';
        currentX -= 2;
        doneXs.add(currentX + 2);
        doneYs.add(currentY);
    }

    static void moveDown(int currentX, int currentY, char[][] board, ArrayList<Integer> doneXs, ArrayList<Integer> doneYs) {
        board[currentY + 1][currentX] = '0';
        board[currentY + 2][currentX] = '*';
        currentY += 2;
        doneXs.add(currentX);
        doneYs.add(currentY - 2);
    }

    static void moveUp(int currentX, int currentY, char[][] board, ArrayList<Integer> doneXs, ArrayList<Integer> doneYs) {
        board[currentY - 1][currentX] = '0';
        board[currentY - 2][currentX] = '*';
        currentY -= 2;
        doneXs.add(currentX);
        doneYs.add(currentY + 2);
    }
}
