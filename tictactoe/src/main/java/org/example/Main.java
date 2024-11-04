package org.example;

import java.util.Scanner;

// Abstract class representing a generic game
abstract class Game {
    protected char[][] board;
    protected char currentPlayer;

    public Game() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    protected void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public abstract void play(); // Abstract method for playing the game

    protected void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    protected boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    protected void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    protected boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }
}

// TicTacToe class extending the Game class
class TicTacToe extends Game {
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || col < 0 || row >= 3 || col >= 3 || board[row][col] != '-') {
                System.out.println("This move is not valid");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkForWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a tie!");
                break;
            }

            changePlayer();
        }
        scanner.close();
    }
}

// Main class to run the game
public class Main {
    public static void main(String[] args) {
        Game game = new TicTacToe(); // Polymorphism: Game reference to TicTacToe object
        game.play();
    }
}